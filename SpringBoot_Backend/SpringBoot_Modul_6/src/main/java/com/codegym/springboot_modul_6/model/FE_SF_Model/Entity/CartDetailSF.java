package com.codegym.springboot_modul_6.model.FE_SF_Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cart_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailSF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "cart_id")
    @ManyToOne
    @JsonIgnore
    private CartSF cartSF;

    private Long quantity;

    private String name;

    private Double price;

    private String serialNumber;


}
