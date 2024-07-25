package com.example.qss.serviceImpl;

import com.example.qss.model.User;
import com.example.qss.repository.UserRepository;
import com.example.qss.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveUser(User user) {
    	if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> getAllUsersSortedByName(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> getAllUsersSortedByAgeDesc(Pageable pageable) {
        return userRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by("age").descending()));
    }

	@Override
	public Optional<User> getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
}