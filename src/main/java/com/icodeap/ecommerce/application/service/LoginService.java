package com.icodeap.ecommerce.application.service;

import com.icodeap.ecommerce.domain.User;
import com.icodeap.ecommerce.domain.UserType;
import com.icodeap.ecommerce.infrastructure.dto.UserDto;

public class LoginService {

    private final UserService userService;

    public LoginService(UserService userService) {
        this.userService = userService;
    }

    public boolean existUser(String email) {
        try {
            User user = userService.findByEmail(email);
        } catch(Exception e) {
            return false;
        }

        return true;
    }

    public Integer getUserId(String email) {
        try{
            return userService.findByEmail(email).getId();
        } catch(Exception e) {
            return 0;
        }
    }

    public UserType getUserType(UserDto userDto) {
            return userService.findByEmail(userDto.getEmail()).getUserType();
    }

    public User getUser(String email) {
        try{
            return userService.findByEmail(email);
        }catch(Exception e){
            return new User();
        }
    }
}
