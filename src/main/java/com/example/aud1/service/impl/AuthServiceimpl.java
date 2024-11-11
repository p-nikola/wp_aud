package com.example.aud1.service.impl;

import com.example.aud1.model.User;
import com.example.aud1.model.exceptions.InvalidArgumentsException;
import com.example.aud1.model.exceptions.InvalidUserCredentialException;
import com.example.aud1.model.exceptions.PasswordsDoNotMatchException;
import com.example.aud1.repository.InMemoryUserRepository;
import com.example.aud1.service.AuthService;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceimpl implements AuthService {

    private final InMemoryUserRepository userRepository;

    public AuthServiceimpl(InMemoryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }

            return userRepository.findByUsernameAndPassword(username,password).orElseThrow(InvalidUserCredentialException::new);

    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }

        if(!password.equals(repeatPassword)){
            throw new PasswordsDoNotMatchException();
        }

        User user = new User(username,password,name,surname);
        return userRepository.saveOrUpdate(user);

    }
}
