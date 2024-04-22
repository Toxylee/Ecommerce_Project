package com.ecoomerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
//@Indexed
@Table(name = "configuration")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
@SequenceGenerator(
        name = "configuration_sequence_gen",
        sequenceName = "configuration_seq",
        allocationSize = 1)
public class Configuration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sequence_gen")
    private Long id;
    @Basic
    @Column(name = "logo_url")
    private String logoUrl;
    @Basic
    @Column(name = "fonts", nullable = false)
    private String fonts;

    @Basic
    @Column(name = "configuration_code", nullable = false)
    private String configurationCode;

    @Basic
    @Column(name = "colour", nullable = false)
    private String colour;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "merchant_fk")
    private Merchant merchant;


}
