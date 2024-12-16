package com.example.aud1.service.impl;

import com.example.aud1.model.Role;
import com.example.aud1.model.User;
import com.example.aud1.model.exceptions.InvalidArgumentsException;
import com.example.aud1.model.exceptions.PasswordsDoNotMatchException;
import com.example.aud1.model.exceptions.UserNotFoundException;
import com.example.aud1.model.exceptions.UsernameAlreadyExistsException;
import com.example.aud1.repository.jpa.UserRepository;
import com.example.aud1.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        if (this.userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }

        User user = new User(username, passwordEncoder.encode(password), name, surname,role);
        userRepository.save(user);
    }

    @Override
    public void changeRole(String username, String newRole) {
        User user= userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));

        user.setRole(Role.valueOf("ROLE_" +newRole.toUpperCase()));

        userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }
}
