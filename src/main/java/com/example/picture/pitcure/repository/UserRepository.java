package com.example.picture.pitcure.repository;

import com.example.picture.pitcure.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByName(String name);
}
