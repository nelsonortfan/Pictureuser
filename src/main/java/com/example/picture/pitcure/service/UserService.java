package com.example.picture.pitcure.service;

import com.example.picture.pitcure.model.User;
import com.example.picture.pitcure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User updateUserPicture(String name, MultipartFile file) throws IOException {
        User user = userRepository.findByName(name);

        byte [] bytes = file.getBytes();
        String base64Image = Base64.getEncoder().encodeToString(bytes);

        user.setPictureBase64(base64Image);
        return userRepository.save(user);
    }

    public User createUser(String name, MultipartFile file) throws IOException{
        User user = new User();
        user.setName(name);
        byte [] bytes = file.getBytes();
        user.setPictureBase64(Base64.getEncoder().encodeToString(bytes));
        return userRepository.save(user);
    }

    public User getUser(String name){
        User user = userRepository.findByName(name);
        return user;
    }


}
