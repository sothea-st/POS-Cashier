package com.example.pos.system.constant.util.exception.customeException;

import com.example.pos.system.constant.JavaMessage;

public class JavaNotFoundByIdGiven extends RuntimeException{
//    public JavaNotFoundByIdGiven() {
//
//    }

    public JavaNotFoundByIdGiven(){
        super(JavaMessage.notFoundById);
    }
}
