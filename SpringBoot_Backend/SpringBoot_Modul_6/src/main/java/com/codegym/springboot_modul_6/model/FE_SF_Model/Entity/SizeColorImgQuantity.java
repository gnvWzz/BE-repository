package com.codegym.springboot_modul_6.model.FE_SF_Model.Entity;

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
    private List<String> img;
}
