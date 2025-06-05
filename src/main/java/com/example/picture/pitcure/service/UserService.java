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

    public User updateUserPicture(Long userId, MultipartFile file) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        byte [] bytes = file.getBytes();
        String base64Image = Base64.getEncoder().encodeToString(bytes);

        user.setPictureBase64(base64Image);
        return userRepository.save(user);
    }


}
