package com.codegym.springboot_modul_6.model.fe_sf_model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailSF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "order_id")
    @ManyToOne
    private OrderSF orderSF;

    private Long quantity;

    private String name;

    private double price;

    private String size_color_img_quantity;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "sub_total")
    private Double subTotal;

    private String isDeleted = "false";

    public OrderDetailSF(OrderSF orderSF, Long quantity, String name, double price, String size_color_img_quantity, String serialNumber, Double subTotal, String isDeleted) {
        this.orderSF = orderSF;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.size_color_img_quantity = size_color_img_quantity;
        this.serialNumber = serialNumber;
        this.subTotal = subTotal;
        this.isDeleted = isDeleted;
    }
}
