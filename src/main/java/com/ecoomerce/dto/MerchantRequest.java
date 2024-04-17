package com.ecoomerce.dto;

import lombok.Data;

@Data
public class MerchantRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String businessName;
}
