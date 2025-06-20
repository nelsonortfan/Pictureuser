package com.example.picture.pitcure.controller;

import com.example.picture.pitcure.model.User;
import com.example.picture.pitcure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class WebController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{name}/profile")
    public String getUserProfile(@PathVariable String name, Model model){

        Optional<User> userOptional = userRepository.findByName(name);

        if(userOptional.isPresent()){
            User user = userOptional.get();
            model.addAttribute("name", user.getName());
            model.addAttribute("pictureBase64", user.getPictureBase64() );
            return "index";
        }
        return "";

    }

}
