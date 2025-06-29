package com.example.picture.pitcure.repository;

import com.example.picture.pitcure.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByName(String name);
}
