package com.sparta.sheduleapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthRequestDto {
    private String email;
    private String memberName;
    private String password;
    private boolean admin = false;

    public void initPassword(String password){
        this.password = password;
    }
}
