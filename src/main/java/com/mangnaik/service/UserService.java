package com.mangnaik.service;

import com.mangnaik.database.InMemoryDatabase;
import com.mangnaik.exceptions.UserNotFoundException;
import com.mangnaik.model.User;
import com.mangnaik.model.UserBalance;

import java.util.*;

public class UserService {

    private InMemoryDatabase database;

    public UserService(InMemoryDatabase database){
        this.database = database;
    }


    public List<UserBalance> getAllBalances() throws UserNotFoundException {
        Map<String, Map<String, Double>> balanceMap = database.getAllBalances();
        List<UserBalance> userBalances = new ArrayList<>();
        for(String id: balanceMap.keySet()){
            userBalances.add(new UserBalance(database.getUserById(id), balanceMap.get(id)));
        }
        return userBalances;
    }

    public UserBalance getBalanceForUser(String userId) throws UserNotFoundException{
        Map<String, Double> balance = database.getBalancesForUser(userId);
        UserBalance userBalance = new UserBalance(database.getUserById(userId), balance);
        return userBalance;
    }

    public String addUser(String name, String email, String phoneNumber) {
        return database.addUser(name, email, phoneNumber);
    }
}
