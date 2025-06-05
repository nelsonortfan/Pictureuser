package com.example.picture.pitcure.controller;

import com.example.picture.pitcure.model.User;
import com.example.picture.pitcure.repository.UserRepository;
import com.example.picture.pitcure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> createuser(@RequestBody User user){
        User saveUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(saveUser);
    }

    @PostMapping("/picture")
    public ResponseEntity<User> createUserWithPicture(@RequestParam("name") String name,
                                                      @RequestParam("file") MultipartFile file){
        try{
            User newUser = userService.createUser(name, file);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }
        catch(IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}/picture")
    public ResponseEntity<User> uploadPicture(@PathVariable Long id,
                                              @RequestParam("file")MultipartFile file){
        try{
            User updateUser = userService.updateUserPicture(id, file);
            return ResponseEntity.ok(updateUser);
        }
        catch(IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}/picture")
    public ResponseEntity<byte[]> getPicture(@PathVariable Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not Found")
        );

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(user.getPictureBase64().getBytes());
    }


    @GetMapping("/{id}/picture3")
    public ResponseEntity<byte[]> getPicture3(@PathVariable Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not Found")
        );

        byte [] imagesBytes = Base64.getDecoder().decode(user.getPictureBase64());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagesBytes);

    }


}
