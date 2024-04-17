package com.ecoomerce.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {
    private String productName;
    private String batchNumber;
    private String productCode;
    private String brandName;
    private String productDescription;
    private int qty;
    private BigDecimal sellingPrice;
    private BigDecimal unitPrice;
    private BigDecimal costPrice;
    private String productImageUrl;
    private String unitOfMeasurement;

}
