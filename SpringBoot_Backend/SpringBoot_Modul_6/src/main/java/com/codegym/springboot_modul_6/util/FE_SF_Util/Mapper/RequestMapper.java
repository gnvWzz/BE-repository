package com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper;


import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.Login;
import com.codegym.springboot_modul_6.Model.FE_SF_Model.dto.LoginDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper {
    @Autowired
    private ModelMapper mapper;

    public Login toLogin(LoginDto loginDto){
        return mapper.map(loginDto, Login.class);
    }
}
