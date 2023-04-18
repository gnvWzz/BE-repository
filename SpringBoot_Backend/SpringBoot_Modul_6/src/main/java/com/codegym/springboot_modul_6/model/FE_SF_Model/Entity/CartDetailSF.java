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
    private CartSF cartSF;

    private Long quantity;

    private String name;

    private double price;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "sub_total")
    private double subTotal;

    private String isDeleted = "false";

    public CartDetailSF(CartSF cartSF, Long quantity, String name, Double price, String serialNumber, Double subTotal, String isDeleted) {
        this.cartSF = cartSF;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.serialNumber = serialNumber;
        this.subTotal = subTotal;
        this.isDeleted = isDeleted;
    }
}
