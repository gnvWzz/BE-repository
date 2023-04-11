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

    @OneToMany(mappedBy = "cartSF", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<CartDetailSF> cartDetailSFS;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "total_price_cart")
    private Double totalPrice;

    public CartSF(List<CartDetailSF> cartDetailSFS, String  accountName, Double totalPrice) {
        this.cartDetailSFS = cartDetailSFS;
        this.accountName = accountName;
        this.totalPrice = totalPrice;
    }
}
