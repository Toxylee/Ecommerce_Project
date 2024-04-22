package com.ecoomerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
//@Indexed
@Table(name = "transaction")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
@SequenceGenerator(
        name = "transaction_sequence_gen",
        sequenceName = "transaction_seq",
        allocationSize = 1)
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_sequence_gen")
    private Long id;


    @Column(name = "product_name")
    private String productName;

    @Column(name = "batch_No")
    private String batchNo;


    @Column(name = "quantity")
    private int quantity;

    @Column(name = "amount")
    private BigDecimal amount;


    @Column(name = "transaction_date")
    private String transactionDate;


    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "last_modified")
    private Date lastModified;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "merchant_fk")
    private Merchant merchant;


}
