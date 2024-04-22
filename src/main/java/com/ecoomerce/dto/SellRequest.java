package com.ecoomerce.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SellRequest {
   private String productName;
   private int quantity;
}
