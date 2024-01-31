package com.gamesstorebe.repository;

import com.gamesstorebe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "userRepository")
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
