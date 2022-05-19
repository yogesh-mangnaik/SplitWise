package com.mangnaik;

/*
Pre register all the users

no database
no rest apis .. create classes and call from driver

no need to simplify the expenses

 */

import com.mangnaik.controller.ExpenseController;
import com.mangnaik.controller.UserController;
import com.mangnaik.database.InMemoryDatabase;
import com.mangnaik.model.User;
import com.mangnaik.model.UserBalance;
import com.mangnaik.service.ExpenseService;
import com.mangnaik.service.UserService;

import java.util.*;

public class Main {
    public static void main(String[] args){
        InMemoryDatabase database = new InMemoryDatabase();
        ExpenseService expenseService = new ExpenseService(database);
        UserService userService = new UserService(database);
        ExpenseController expenseController = new ExpenseController(expenseService);
        UserController userController = new UserController(userService);

        //adding users
        userController.addUser("user1", "user1@gmail.com", "1234567891");
        userController.addUser("user2", "user1@gmail.com", "1234567891");
        userController.addUser("user3", "user1@gmail.com", "1234567891");
        userController.addUser("user4", "user1@gmail.com", "1234567891");
        userController.addUser("user5", "user1@gmail.com", "1234567891");

        //take inputs
        Scanner scan = new Scanner(System.in);

        while(true){
            String input = scan.nextLine();
            String[] parts = input.split(" ");
            String command = parts[0];

            switch(command){
                case "SHOW":
                    if(parts.length == 1){
                        int count = 0;
                        List<UserBalance> balances = userController.getAllBalances();
                        for(UserBalance balance: balances){
                            for(String id: balance.getBalances().keySet()){
                                if (balance.getBalances().get(id) > 0) {
                                    count++;
                                    System.out.println(balance.getUser().getUserId() + " owes " + id + ": " + balance.getBalances().get(id));
                                }
                            }
                        }
                        if(count == 0){
                            System.out.println("No Balances");
                        }
                    }
                    else{
                        int count = 0;
                        UserBalance balance = userController.getBalanceForUser(parts[1]);
                        for(String id: balance.getBalances().keySet()){
                            if (balance.getBalances().get(id) > 0) {
                                count++;
                                System.out.println(balance.getUser().getUserId() + " owes " + id + ": " + balance.getBalances().get(id));
                            }
                            else if(balance.getBalances().get(id) < 0){
                                count++;
                                System.out.println(id + " owes " + balance.getUser().getUserId() + ": " + (-1 * balance.getBalances().get(id)));
                            }
                        }
                        if (count == 0) {
                            System.out.println("No Balances");
                        }
                    }
                    break;
                case "EXPENSE":
                    int index = 1;
                    String payer = parts[index++];
                    long amount = Long.parseLong(parts[index++]);
                    int userCount = Integer.parseInt(parts[index++]);
                    List<String> users = new ArrayList<>();
                    for(int i=0; i<userCount; i++){
                        users.add(parts[index++]);
                    }
                    String type = parts[index++];
                    List<Long> amounts = null;
                    switch(type){
                        case "EXACT":
                            amounts = new ArrayList<>();
                            for(int i=0; i<userCount; i++){
                                amounts.add(Long.parseLong(parts[index++]));
                            }
                    }
                    expenseController.addExpense(payer, users, amount, amounts, type);
                    break;
            }
        }
    }
}