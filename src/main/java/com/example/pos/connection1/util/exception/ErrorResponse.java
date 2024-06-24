package com.example.pos.connection1.util.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ErrorResponse<T> {
     private int code;
     private T reason;
}
