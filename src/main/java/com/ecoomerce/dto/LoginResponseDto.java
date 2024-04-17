package com.ecoomerce.dto;


import com.ecoomerce.model.Merchant;

public class LoginResponseDto {
    private Merchant merchant;
    private String jwt;

    public LoginResponseDto(){
        super();
    }

    public LoginResponseDto(Merchant merchant, String jwt){
        this.merchant = merchant;
        this.jwt = jwt;
    }

    public Merchant getUser(){
        return this.merchant;
    }

    public void setUser(Merchant merchant){
        this.merchant = merchant;
    }

    public String getJwt(){
        return this.jwt;
    }

    public void setJwt(String jwt){
        this.jwt = jwt;
    }

}
