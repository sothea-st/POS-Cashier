/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.main_validation.ResponseException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author MOBILE-APP.02
 */
@Setter
@Getter
@NoArgsConstructor
public class ResponseErrorChangePassword {
     private String msg;
     private String newPassword;
     private String confirmPassword;
}
