package com.example.pos.connection1.util.response;

import lombok.Builder;

@Builder
public record JavaMessageResponse<T>(
     int status,
     String msg,
     T data
) {
     public static String success = "Success";
     public static String insertSuccess = "Insert Success";
     public static String deleteSuccess = "Delete Success";

}
