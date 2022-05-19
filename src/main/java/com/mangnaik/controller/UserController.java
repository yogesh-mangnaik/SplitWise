package com.mangnaik.controller;

import com.mangnaik.exceptions.UserNotFoundException;
import com.mangnaik.model.UserBalance;
import com.mangnaik.service.UserService;

import java.util.List;

public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    public List<UserBalance> getAllBalances() {
        List<UserBalance> list = null;
        try{
            list = userService.getAllBalances();
        }
        catch(UserNotFoundException e){
            e.printStackTrace();
        }
        return list;
    }

    public UserBalance getBalanceForUser(String userId) {
        UserBalance userBalance = null;
        try{
            userBalance =  userService.getBalanceForUser(userId);
        }
        catch(UserNotFoundException exception){
            exception.printStackTrace();
        }
        return userBalance;
    }

    public String addUser(String name, String email, String phoneNumber){
        return userService.addUser(name, email, phoneNumber);
    }
}
