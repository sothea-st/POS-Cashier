package com.example.pos.connection1.util.exception.customeException;

public class InValidEmail extends RuntimeException{
    public InValidEmail(){}
    public InValidEmail(String message) {
        super(message);
    }
}
