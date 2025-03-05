package com.example.mycommunity.repositories;

import com.example.mycommunity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom method to find user by door number
    Optional<User> findByDoorNo(String doorNo);
}
