package com.ecoomerce.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryRequest {
    private String name;
    private String description;
    private String imageUrl;

}
