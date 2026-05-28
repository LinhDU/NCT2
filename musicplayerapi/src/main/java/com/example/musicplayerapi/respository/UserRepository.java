package com.example.musicplayerapi.respository;

import com.example.musicplayerapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}