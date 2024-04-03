package com.example.pos.connection1.util.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {
    private int statusCode;
    private String message;

    public ResponseError(String message){
        super();
        this.message = message;
    }
}
