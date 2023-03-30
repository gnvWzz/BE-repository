package com.codegym.springboot_modul_6.Model.FE_BO_Model.Entity;

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
    @Column(name = "phone")
    private String phone;
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
    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ManufacturerDetail> manufacturerDetails = new ArrayList<>();
}
