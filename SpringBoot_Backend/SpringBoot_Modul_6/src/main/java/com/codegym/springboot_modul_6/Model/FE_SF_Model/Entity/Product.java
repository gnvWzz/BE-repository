package com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private String serial_number;
    private String size;
    private String color;
    private Long price;
    private String brief_description;
    private String full_description;
    private String manufacturer;
    private Long weight;
    private String material;
    private String status;
    private Long quantity;

    @OneToMany(mappedBy = "product",cascade=CascadeType.ALL)
    private List<Image> imageList;

    public Product(String name, String category, String serial_number, String size, String color, Long price, String brief_description, String full_description, String manufacturer, Long weight, String material, String status, Long quantity) {
        this.name = name;
        this.category = category;
        this.serial_number = serial_number;
        this.size = size;
        this.color = color;
        this.price = price;
        this.brief_description = brief_description;
        this.full_description = full_description;
        this.manufacturer = manufacturer;
        this.weight = weight;
        this.material = material;
        this.status = status;
        this.quantity = quantity;
    }
}
