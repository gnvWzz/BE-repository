package com.codegym.springboot_modul_6.model.FE_BO_Model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "rolebo")
public class RoleBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "roleBO")
    private List<MemberRole> memberRoles;
}
