package com.mangnaik.exceptions;

import com.mangnaik.model.User;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String message){
        super(message);
    }

}
