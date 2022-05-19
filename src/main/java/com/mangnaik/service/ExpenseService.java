package com.mangnaik.service;

import com.mangnaik.database.InMemoryDatabase;
import com.mangnaik.exceptions.UserNotFoundException;
import com.mangnaik.model.User;

import java.util.*;

public class ExpenseService {

    private InMemoryDatabase database;

    public ExpenseService(InMemoryDatabase database){
        this.database = database;
    }

    public void addExpense(String userWhoPaid, List<String> userId, long amount, List<Long> splits, String type) throws UserNotFoundException {
        List<User> users = new ArrayList<>();
        User payingUser = database.getUserById(userWhoPaid);
        for(String user: userId){
            users.add(database.getUserById(user));
        }
        switch(type){
            case "EQUAL":
                // TODO: handle case of unequal
                double splitAmount = (double) amount / users.size();
                for(User dest: users){
                    database.addExpense(payingUser, dest, splitAmount);
                }
                break;
            case "EXACT":
                //validation
                long sum = 0;
                for(long am: splits){
                    sum += am;
                }
                if(sum != amount){
                    throw new RuntimeException("Amount and splits don't add up");
                }
                for(int index=0; index<users.size(); index++){
                    database.addExpense(payingUser, users.get(index), splits.get(index));
                }
                break;
        }
    }
}
