package com.icodeap.ecommerce.application.service;

import com.icodeap.ecommerce.application.repository.UserRepository;
import com.icodeap.ecommerce.domain.User;

public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User createUser(User user){
        return repo.createUser(user);
    }

    public User findByEmail(String email){
        return repo.findByEmail(email);
    }

    public User findById(Integer id){
        return repo.findById(id);
    }
}
