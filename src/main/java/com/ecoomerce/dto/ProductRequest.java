package com.ecoomerce.dto;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductRequest {

    private String productName;

    private String brandName;

    private String productDescription;
private String categoryCode;

    private int qty;

    private BigDecimal sellingPrice;

    private BigDecimal unitPrice;

    private BigDecimal costPrice;

    private String productImageUrl;

    private String unitOfMeasurement;

}
