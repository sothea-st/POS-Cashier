package com.example.pos.system.constant.util.exception;

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
