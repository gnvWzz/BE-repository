package com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductBODto implements Serializable {

    private String name;
    private String category;

    private String packageId;
    private String status;
    private String manufacturer;

    private List<RequestProductBODetailDto> requestProductBODetailDtos;

}
