package com.example.pos.connection1.util.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class FieldErrorResponse {
     private String field;
     private String detail;
}
