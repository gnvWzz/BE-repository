package com.codegym.springboot_modul_6.service.FE_BO_Service.impl;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestMemberDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseMemberDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseMemberRoleDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.Member;
import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.MemberRole;
import com.codegym.springboot_modul_6.repository.FE_BO_Repository.MemberRepository;
import com.codegym.springboot_modul_6.service.FE_BO_Service.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Optional<ResponseMemberDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<ResponseMemberDto> findAll(Pageable pageable) {
        Page<Member> accountBOs = memberRepository.findAll(pageable);
        List<ResponseMemberDto> responseMemberDtoList = new ArrayList<>();
        for (Member acc : accountBOs) {
            ResponseMemberDto responseMemberDto = new ResponseMemberDto();
            BeanUtils.copyProperties(acc, responseMemberDto);

            List<MemberRole> memberRoles = acc.getMemberRoles();
            List<ResponseMemberRoleDto> responseMemberRoleDtoList = new ArrayList<>();
            for (MemberRole ele : memberRoles) {
                ResponseMemberRoleDto responseMemberRoleDto = new ResponseMemberRoleDto();
                BeanUtils.copyProperties(ele, responseMemberRoleDto);

                Long memberId = ele.getMember().getId();
                responseMemberRoleDto.setMemberId(memberId);
                String memberName = ele.getMember().getFullName();
                responseMemberRoleDto.setMemberName(memberName);
                Long roleBOId = ele.getRoleBO().getId();
                responseMemberRoleDto.setRoleBOId(roleBOId);

                responseMemberRoleDtoList.add(responseMemberRoleDto);
            }
            responseMemberDto.setResponseMemberRoleDtoList(responseMemberRoleDtoList);

            responseMemberDtoList.add(responseMemberDto);
        }
        return new PageImpl<>(responseMemberDtoList, pageable, accountBOs.getTotalElements());
    }

    @Override
    public RequestMemberDto save(RequestMemberDto requestMemberDto) {
        return null;
    }

    @Override
    public boolean block(Long id) {
        if (id != null) {
            Member member = memberRepository.findById(id).orElse(null);
            if (member != null) {
                if (member.getStatus().equals("UNLOCKED")) {
                    member.setStatus("BLOCKED");
                } else {
                    member.setStatus("UNLOCKED");
                }
                memberRepository.save(member);
                return true;
            }
        }
        return false;
    }
}