package com.codegym.springboot_modul_6.model.FE_SF_Model.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "cart")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartSF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cartSF", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<CartDetailSF> cartDetailSFS;

    @Column(name = "account_name")
    private String accountName;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "total_price_cart")
    private double totalPrice;

    private String isDeleted = "false";

    public CartSF(List<CartDetailSF> cartDetailSFS, String accountName, Account account, double totalPrice, String isDeleted) {
        this.cartDetailSFS = cartDetailSFS;
        this.accountName = accountName;
        this.account = account;
        this.totalPrice = totalPrice;
        this.isDeleted = isDeleted;
    }
}
