package com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @JoinColumn(name = "product_id")
    @ManyToOne
    @JsonIgnore
    private ProductSF productSF;



    public Image(String url) {
        this.url = url;
    }
}
