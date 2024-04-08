package com.example.pos.connection1.util.exception.customeException;

import com.example.pos.connection1.constant.JavaMessage;

public class JavaNotFoundByIdGiven extends RuntimeException{
//    public JavaNotFoundByIdGiven() {
//
//    }

    public JavaNotFoundByIdGiven(){
        super(JavaMessage.notFoundById);
    }
}
