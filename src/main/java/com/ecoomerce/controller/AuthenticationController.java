package com.ecoomerce.controller;


import com.ecoomerce.dto.LoginResponseDto;
import com.ecoomerce.dto.MerchantRequest;
import com.ecoomerce.dto.MerchantResponse;
import com.ecoomerce.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public MerchantResponse registerUser(@RequestBody MerchantRequest request){
        return authenticationService.registerMerchant(request);
    }

    @PostMapping("/login")
    public LoginResponseDto loginUser(@RequestParam("email")String email, @RequestParam("password")String password){
        return authenticationService.loginUser(email, password);
    }
}