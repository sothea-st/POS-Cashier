package com.example.pos.system.constant.util.exception.customeException;

import java.util.HashMap;

public class FieldIsRequiredException extends RuntimeException{
    public FieldIsRequiredException() {}

    public FieldIsRequiredException(String msg)
    {
        super(msg);
    }

    public FieldIsRequiredException(HashMap<String, Object> map) {
        
       
    }
}
