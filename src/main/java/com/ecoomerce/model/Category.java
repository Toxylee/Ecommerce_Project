package com.ecoomerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
//@Indexed
@Table(name = "category")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
@SequenceGenerator(
        name = "category_sequence_gen",
        sequenceName = "category_seq",
        allocationSize = 1)
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sequence_gen")
    private Long id;


    @Column(name = "category_name")
    private String categoryName;


    @Column(name = "description")
    private String description;


    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "category_code")
    private String categoryCode;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    @Builder.Default
    private Set<Product> products = new LinkedHashSet<>();


}
