package com.example.pos.authentication.services;

import com.example.pos.authentication.entity.User;
import com.example.pos.authentication.repositories.UserRepository;

import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    @Qualifier("entityManagerFactory1")
    private EntityManager entityManager1;

    @Autowired
    @Qualifier("entityManagerFactory2")
    private EntityManager entityManager2;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}
