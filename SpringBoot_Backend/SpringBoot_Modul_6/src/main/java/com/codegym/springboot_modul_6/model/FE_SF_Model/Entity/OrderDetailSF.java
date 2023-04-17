package com.codegym.springboot_modul_6.model.FE_SF_Model.Entity;

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

    private Double price;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "sub_total")
    private Double subTotal;

    private String isDeleted = "false";

    public OrderDetailSF(OrderSF orderSF, Long quantity, String name, Double price, String serialNumber, Double subTotal, String isDeleted) {
        this.orderSF = orderSF;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.serialNumber = serialNumber;
        this.subTotal = subTotal;
        this.isDeleted = isDeleted;
    }
}
