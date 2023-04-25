package com.codegym.springboot_modul_6.model.fe_sf_model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SizeColorImgQuantity {
    private String size;
    private String color;
    private Long quantity;
    private List<Img> img;
}

