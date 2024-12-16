package com.example.aud1.service;

import com.example.aud1.model.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void register(String username, String password, String repeatPassword, String name, String surname, Role role);
    void changeRole(String username,String newRole);

}
