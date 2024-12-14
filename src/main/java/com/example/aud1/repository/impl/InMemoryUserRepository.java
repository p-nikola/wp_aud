package com.example.aud1.repository.impl;


import com.example.aud1.bootstrap.DataHolder;
import com.example.aud1.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryUserRepository {

    public Optional<User> findByUsername(String username) {
        return DataHolder.users.stream().filter(i -> i.getUsername().equals(username)).findFirst();
    }


    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return DataHolder.users.stream().filter(i -> i.getUsername().equals(username) && i.getPassword().equals(password)).findFirst();
    }


    public User saveOrUpdate(User user) {
        DataHolder.users.removeIf(i -> i.getUsername().equals(user.getUsername()));
        DataHolder.users.add(user);

        return user;
    }

    public void delete(String username){
        DataHolder.users.removeIf(i->i.getUsername().equals(username));

    }


}
