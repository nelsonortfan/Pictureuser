package com.example.picture.pitcure.controller;

import com.example.picture.pitcure.exception.UserAlreadyExistsException;
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
    public ResponseEntity<User> createUserWithPicture(@RequestParam("name") String name,
                                                      @RequestParam("file") MultipartFile file){
        try{
            User newUser = userService.createUser(name, file);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }
        catch(IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}/picture")
    public ResponseEntity<User> uploadPicture(@PathVariable String name,
                                              @RequestParam("file")MultipartFile file){
        try{
            User updateUser = userService.updateUserPicture(name, file);
            return ResponseEntity.ok(updateUser);
        }
        catch(IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/{name}/picture")
    public ResponseEntity<byte[]> getPicture(@PathVariable String name){
        User user = userService.getUser(name);

        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        byte [] imagesBytes = Base64.getDecoder().decode(user.getPictureBase64());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagesBytes);

    }


}
