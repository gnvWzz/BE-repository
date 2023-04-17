package com.codegym.springboot_modul_6.model.FE_BO_Model.entity;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Account;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSFDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "store")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    private String image;
    @JoinColumn(name = "account_id")
    @OneToOne
    @JsonIgnore
    private Account account;
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ProductSF> productSF;
}
