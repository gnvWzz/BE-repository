package com.codegym.springboot_modul_6.service.FE_BO_Service;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestMemberDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseMemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MemberService {
    Optional<ResponseMemberDto> findById(Long id);
    Page<ResponseMemberDto> findAll(Pageable pageable);
    ResponseMemberDto save(RequestMemberDto requestMemberDto);

    ResponseMemberDto update(RequestMemberDto requestMemberDto);

    boolean block(Long id);

    boolean addImage(Long id, String image);
}
