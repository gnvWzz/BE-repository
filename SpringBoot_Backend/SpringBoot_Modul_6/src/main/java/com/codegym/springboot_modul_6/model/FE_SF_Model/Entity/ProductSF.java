package com.codegym.springboot_modul_6.model.FE_SF_Model.Entity;

import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.Store;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSF implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;

    @Column(name = "package_id")
    private String packageId;
    private String status;
//    private String manufacturer;

    @OneToMany(mappedBy = "productSF", fetch = FetchType.LAZY)
    private List<ProductSFDetail> productSFDetail;

    @OneToMany(mappedBy = "productSF", fetch = FetchType.LAZY)
    private List<PriceList> prices;

    @JoinColumn(name = "store_id")
    @ManyToOne
    @JsonIgnore
    private Store store;

}
