package com.example.picture.pitcure.service;

import com.example.picture.pitcure.exception.UserAlreadyExistsException;
import com.example.picture.pitcure.exception.UserNotFoundException;
import com.example.picture.pitcure.model.User;
import com.example.picture.pitcure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User updateUserPicture(String name, MultipartFile file) throws IOException {
        Optional<User> optionalUser = userRepository.findByName(name);

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            byte [] bytes = file.getBytes();
            String base64Image = Base64.getEncoder().encodeToString(bytes);
            user.setPictureBase64(base64Image);
            return userRepository.save(user);
        }
        else {
            throw new UserNotFoundException(name);
        }
    }

    public User createUser(String name, MultipartFile file) throws IOException, UserAlreadyExistsException {
        Optional<User> optionalUser = userRepository.findByName(name);
        if(optionalUser.isPresent()){
            throw  new UserAlreadyExistsException("The user " + name + " already exists");
        }
        User user = new User();
        user.setName(name);
        byte [] bytes = file.getBytes();
        user.setPictureBase64(Base64.getEncoder().encodeToString(bytes));
        return userRepository.save(user);
    }

    public User getUser(String name){
        Optional<User> optionalUser = userRepository.findByName(name);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        else {
            throw new UserNotFoundException(name);
        }
    }

    public User save(String name, String imageBase64){
        Optional<User> optionalUser = userRepository.findByName(name);
        if(optionalUser.isPresent()){
            User userSaved = optionalUser.get();
            userSaved.setPictureBase64(imageBase64);
            userRepository.save(userSaved);
            return userSaved;
        }
        else {
            throw new UserNotFoundException(name);
        }
    }

}
