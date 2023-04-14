package com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
//bỏ qua các thuộc tính null cua Vòng lặp vô hạn trong JSON khi một đối tượng chứa 1 tham chiếu đệ quy
public class ResponseMemberDto {
    private Long id;
    private String fullName;
    private String password;
    private String mobile;
    private String landline;
    private String email;
    private String address;
    private Date signup;
    private String status;
    private String image;
    List<ResponseMemberRoleDto> responseMemberRoleDtoList;
}
