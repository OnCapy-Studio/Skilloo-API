package com.skilloo.api.services.exceptions;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(String msg){
        super(msg);
    }
}
