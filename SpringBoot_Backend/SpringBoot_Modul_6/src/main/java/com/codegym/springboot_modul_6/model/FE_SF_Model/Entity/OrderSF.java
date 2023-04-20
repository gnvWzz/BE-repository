package com.codegym.springboot_modul_6.model.FE_SF_Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ordersf")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderSF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "first_name_order")
    private String firstName;

    @Column(name = "last_name_order")
    private String lastName;

    private String city;

    private String street;

    private String district;

    private String phone;

    private String email;

    @Column(name = "ordercode")
    private String orderCode;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "is_deleted")
    private String isDeleted = "false";

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


    @Column(name = "date_order")
    private String dateOrder;

    @OneToMany(mappedBy = "orderSF", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<OrderDetailSF> orderDetailSFS;


}
