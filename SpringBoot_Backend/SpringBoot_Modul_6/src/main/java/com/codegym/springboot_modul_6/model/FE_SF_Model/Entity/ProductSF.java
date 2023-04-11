package com.codegym.springboot_modul_6.model.FE_SF_Model.Entity;

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
    private String manufacturer;

    @OneToMany(mappedBy = "productSF", fetch = FetchType.LAZY)
    private List<ProductSFDetail> productSFDetail;

    public ProductSF(String name, String category, String packageId, String status, String manufacturer, List<ProductSFDetail> productSFDetail) {
        this.name = name;
        this.category = category;
        this.packageId = packageId;
        this.status = status;
        this.manufacturer = manufacturer;
        this.productSFDetail = productSFDetail;
    }
}
