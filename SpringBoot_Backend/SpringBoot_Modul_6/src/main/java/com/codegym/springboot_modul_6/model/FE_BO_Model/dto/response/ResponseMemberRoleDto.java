package com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMemberRoleDto {
    private Long id;
    private Long memberId;
    private String memberName;
    private Long roleBOId;
    private String roleBOName;
}
