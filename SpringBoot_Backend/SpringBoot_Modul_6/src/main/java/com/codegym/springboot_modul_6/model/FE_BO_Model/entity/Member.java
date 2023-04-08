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
@Table(name = "`member`")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "`password`")
    private String password;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "landline")
    private String landline;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "signup")
    private Date signup;
    @Column(name = "`status`")
    private String status;
    @Column(name = "icon")
    private String icon;
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MemberRole> memberRoles = new ArrayList<>();
}
