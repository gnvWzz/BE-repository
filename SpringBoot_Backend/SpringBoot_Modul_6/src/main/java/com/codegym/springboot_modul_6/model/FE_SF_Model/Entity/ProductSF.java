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
    private String serial_number;
    private String size;
    private String colors;
    private Double price;
    private String brief_description;
    private String full_description;
    private String manufacturer;
    private Double weight;
    private String material;
    private String status;
    private Long quantity;
    private String cpu;
    private String gpu;
    private String ram;

    @Column(name = "storage_drive")
    private String storageDrive;

    private String display;

    @OneToMany(mappedBy = "productSF")
    private List<Image> imageList;

    public ProductSF(String name, String category, String serial_number, String size, String colors, Double price, String brief_description, String full_description, String manufacturer, Double weight, String material, String status, Long quantity, String cpu, String gpu, String ram, String storageDrive, String display, List<Image> imageList) {
        this.name = name;
        this.category = category;
        this.serial_number = serial_number;
        this.size = size;
        this.colors = colors;
        this.price = price;
        this.brief_description = brief_description;
        this.full_description = full_description;
        this.manufacturer = manufacturer;
        this.weight = weight;
        this.material = material;
        this.status = status;
        this.quantity = quantity;
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
        this.storageDrive = storageDrive;
        this.display = display;
        this.imageList = imageList;
    }
}
