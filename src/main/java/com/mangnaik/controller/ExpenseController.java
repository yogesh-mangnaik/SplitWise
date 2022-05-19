package com.mangnaik.controller;

import com.mangnaik.model.User;
import com.mangnaik.service.ExpenseService;

import java.util.*;

public class ExpenseController {

    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    public void addExpense(String userWhoPaid, List<String> users, long amount, List<Long> splits, String type){
        try{
            expenseService.addExpense(userWhoPaid, users, amount, splits, type);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
