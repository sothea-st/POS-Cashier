package com.example.pos.connection1.authentication.dtos;

import lombok.Getter;

 
public record UserLogoutDTO(
          Integer id,
          String userCode,
          String posId) {

}
