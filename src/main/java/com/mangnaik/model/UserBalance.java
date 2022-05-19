package com.mangnaik.model;

import java.util.Map;

public class UserBalance {
    User user;
    Map<String, Double> balances;

    public UserBalance(User user, Map<String, Double> balances){
        this.user = user;
        this.balances = balances;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, Double> getBalances() {
        return balances;
    }

    public void setBalances(Map<String, Double> balances) {
        this.balances = balances;
    }
}
