package com.codegym.springboot_modul_6.model.fe_sf_model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "price_list")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductSF productSF;

    @Column(name = "price_id")
    private Long priceId;

    @Column(name = "from_quantity")
    private Long fromQuantity;

    @Column(name = "to_quantity")
    private Long toQuantity;

    private Double price;

    public Price(ProductSF productSF, Long priceId, Long fromQuantity, Long toQuantity, Double price) {
        this.productSF = productSF;
        this.priceId = priceId;
        this.fromQuantity = fromQuantity;
        this.toQuantity = toQuantity;
        this.price = price;
    }
}
