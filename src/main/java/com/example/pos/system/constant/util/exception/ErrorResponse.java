package com.example.pos.system.constant.util.exception;

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
