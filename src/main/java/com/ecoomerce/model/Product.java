package com.ecoomerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
//@Indexed
@Table(name = "product")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
@SequenceGenerator(
        name = "product_sequence_gen",
        sequenceName = "merchant_seq",
        allocationSize = 1)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence_gen")
    private Long id;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_name",unique = true)
    private String productName;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "description")
    private String productDescription;

    @Column(name = "quantity")
    private int qty;

    @Column(name = "batch_number")
    private String batchNumber;

    @Column(name = "date_sold")
    private Date dateSold;

    @Column(name = "selling_price")
    private BigDecimal sellingPrice;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "cost_price")
    private BigDecimal costPrice;

    @Column(name = "image_url")
    private String productImageUrl;

    @Column(name = "uom")
    private String unitOfMeasurement;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "last_modified")
    private Date lastModified;

    @ManyToOne
    @JoinColumn(name = "category_fk")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "merchant_fk")
    private Merchant merchant;

    @Field
    public String getDisplayName() {
        StringBuilder displayNameBuilder = new StringBuilder();

        if (Objects.nonNull(brandName)) {
            displayNameBuilder.append(brandName).append(" ");
        }
        if (Objects.nonNull(productName)) {
            displayNameBuilder.append(productName).append(" ");
        }
        if (Objects.nonNull(qty) && qty > 1) {
            displayNameBuilder.append(qty).append(" ");
        }
        return displayNameBuilder.toString().trim();
    }

}
