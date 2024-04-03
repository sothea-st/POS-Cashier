package com.example.pos.connection1.authentication.dtos;

public class LoginUserDto {
    private String userCode;
    private String password;
    private String deviceName;

    public String getDeviceName() {
        return deviceName;
    }

    public String getUserCode() {
        return userCode;
    }

    public LoginUserDto setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginUserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "LoginUserDto{" +
                "userCode='" + userCode + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}