package com.mangnaik.database;

import com.mangnaik.exceptions.UserNotFoundException;
import com.mangnaik.model.User;
import com.mangnaik.model.UserBalance;

import java.util.*;

public class InMemoryDatabase {

    private Map<String, User> users = new HashMap<>();
    private Map<String, Double> expense = new HashMap<>();
    private Map<String, Map<String, Double>> balance = new HashMap();

    int userIndex = 1;

    public User getUserById(String userId) throws UserNotFoundException{
        if(!users.containsKey(userId)){
            throw new UserNotFoundException("User with id: " + userId + " not found");
        }
        return users.get(userId);
    }

    public List<User> getAllUser(){
        List<User> allUsers = new ArrayList();
        allUsers.addAll(users.values());
        return allUsers;
    }

    public void addExpense(User src, User dest, double amount){
        balance.get(src.getUserId()).putIfAbsent(dest.getUserId(), 0.0);
        balance.get(dest.getUserId()).putIfAbsent(src.getUserId(), 0.0);
        Map<String, Double> srcBalance = balance.get(src.getUserId());
        Map<String, Double> destBalance = balance.get(dest.getUserId());
        srcBalance.put(dest.getUserId(), srcBalance.get(dest.getUserId()) - amount);
        destBalance.put(src.getUserId(), destBalance.get(src.getUserId()) + amount);

        expense.putIfAbsent(src.getUserId(), 0.0);
        expense.put(src.getUserId(), expense.get(src.getUserId()) + amount);
    }

    public InMemoryDatabase(){

    }

    public Map<String, Map<String, Double>> getAllBalances() {
        return balance;
    }

    public Map<String, Double> getBalancesForUser(String user){
        return balance.get(user);
    }

    public String addUser(String name, String email, String phoneNumber) {
        String userId = "user" + userIndex;
        users.put(userId, new User("user" + userIndex, name, email, phoneNumber));
        balance.put(userId, new HashMap());
        userIndex++;
        return "user" + userIndex;
    }
}
