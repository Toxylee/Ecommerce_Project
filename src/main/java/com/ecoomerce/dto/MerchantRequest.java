package com.ecoomerce.dto;

import com.ecoomerce.validation.ExtendedEmailValidator;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MerchantRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String businessName;
}
