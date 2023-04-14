//package com.codegym.springboot_modul_6.model.FE_BO_Model.entity;
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Table(name = "manufacturer")
//public class Manufacturer {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(name = "business_code")
//    private String businessCode;
//    @Column(name = "`name`")
//    private String name;
//    @Column(name = "mobile")
//    private String mobile;
//    @Column(name = "landline")
//    private String landline;
//    @Column(name = "email")
//    private String email;
//    @Column(name = "address")
//    private String address;
//    @Column(name = "`field`")
//    private String field;
//    @Column(name = "signup")
//    private Date signup;
//    @Column(name = "website")
//    private String website;
//    @Column(name = "`status`")
//    private String status;
//    @Column(name = "image")
//    private String image;
//    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    private List<ManufacturerProductBO> manufacturerProductBOS = new ArrayList<>();
//
//}
