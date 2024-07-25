
package com.example.qss.services;

import com.example.qss.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Page<User> getAllUsers(Pageable pageable);

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email); // New method for finding by email

    User saveUser(User user);

    void deleteUser(Long id);

    Page<User> getAllUsersSortedByName(Pageable pageable);

    Page<User> getAllUsersSortedByAgeDesc(Pageable pageable);
}
