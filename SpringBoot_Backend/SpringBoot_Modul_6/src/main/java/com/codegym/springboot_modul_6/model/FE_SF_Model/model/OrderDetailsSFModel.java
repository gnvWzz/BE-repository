package com.codegym.springboot_modul_6.model.FE_SF_Model.model;

public class OrderDetailsSFModel {
    private Long quantity;

    private String name;

    private double price;

    private Double subTotal;

    private String size_color_img_quantity;

    public OrderDetailsSFModel() {
    }

    public OrderDetailsSFModel(Long quantity, String name, double price, Double subTotal, String size_color_img_quantity) {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.subTotal = subTotal;
        this.size_color_img_quantity = size_color_img_quantity;
    }

    public String getSize_color_img_quantity() {
        return size_color_img_quantity;
    }

    public void setSize_color_img_quantity(String size_color_img_quantity) {
        this.size_color_img_quantity = size_color_img_quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
}
