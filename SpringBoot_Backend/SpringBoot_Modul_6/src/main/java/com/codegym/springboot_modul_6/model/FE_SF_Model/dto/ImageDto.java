package com.codegym.springboot_modul_6.model.FE_SF_Model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties
public class ImageDto implements Serializable {

    private String url;

    public ImageDto() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
