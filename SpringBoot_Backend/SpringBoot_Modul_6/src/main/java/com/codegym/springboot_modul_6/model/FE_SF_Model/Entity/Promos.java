package com.codegym.springboot_modul_6.model.FE_SF_Model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "promos")
public class Promos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
