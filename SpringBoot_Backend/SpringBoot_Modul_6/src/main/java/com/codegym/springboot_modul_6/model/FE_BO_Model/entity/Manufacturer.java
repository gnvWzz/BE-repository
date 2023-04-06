package com.codegym.springboot_modul_6.model.FE_BO_Model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "manufacturer")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "`name`")
    private String name;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "landline")
    private String landline;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "`field`")
    private String field;
    @Column(name = "signup")
    private Date signup;
    @Column(name = "website")
    private String website;
    @Column(name = "`status`")
    private String status;
    @Column(name = "icon")
    private String icon;
    //    khi xoa mot Manufacturer, các ManufacturerDetail lien quan khong bi xoa
    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ManufacturerDetail> manufacturerDetails = new ArrayList<>();

}