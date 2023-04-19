package com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Account;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.OrderSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Province;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.AccountDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProvinceDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.model.OrderSFModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestMapper {
    public Account toAccount(AccountDto accountDto) {
        Account account = new Account();
        BeanUtils.copyProperties(accountDto, account);
        return account;
    }

    public AccountDto toAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        BeanUtils.copyProperties( account,accountDto);
        return accountDto;
    }



    public List<ProvinceDto> provinceDtoList (List<Province> provinces){
        List<ProvinceDto> provinceDtoList = new ArrayList<>();
        provinceDtoList= provinces.stream().map(this::toProvinceDto).collect(Collectors.toList());

        return provinceDtoList;
    }
    public ProvinceDto toProvinceDto(Province province) {
        ProvinceDto provinceDto = new ProvinceDto();
        BeanUtils.copyProperties(province, provinceDto);
        return provinceDto;
    }


    public List<OrderSFModel> orderSFModelList(List<OrderSF> orderSFList){
        List<OrderSFModel> orderSFModelList = new ArrayList<>();
        orderSFModelList = orderSFList.stream().map(this::toOrderSFModel).collect(Collectors.toList());
        return orderSFModelList;
    }

    private OrderSFModel  toOrderSFModel(OrderSF orderSF) {
        OrderSFModel orderSFModel  =new OrderSFModel();
        BeanUtils.copyProperties(orderSF,orderSFModel);
        return orderSFModel;
    }

}
