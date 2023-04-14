//package com.codegym.springboot_modul_6.service.FE_BO_Service.impl;
//
//import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestMemberDto;
//import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestMemberRoleDto;
//import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseMemberDto;
//import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseMemberRoleDto;
//import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.Member;
//import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.MemberRole;
//import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.RoleBO;
//import com.codegym.springboot_modul_6.repository.FE_BO_Repository.MemberRepository;
//import com.codegym.springboot_modul_6.repository.FE_BO_Repository.MemberRoleRepository;
//import com.codegym.springboot_modul_6.repository.FE_BO_Repository.RoleBORepository;
//import com.codegym.springboot_modul_6.service.FE_BO_Service.MemberService;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class MemberServiceImpl implements MemberService {
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private RoleBORepository roleBORepository;
//    @Autowired
//    private MemberRoleRepository memberRoleRepository;
//    @Override
//    @Transactional
//    public Optional<ResponseMemberDto> findById(Long id) {
//        Member member = memberRepository.findById(id).orElse(null);
//        if(member != null){
//            ResponseMemberDto dto = new ResponseMemberDto();
//            BeanUtils.copyProperties(member, dto);
//
//            List<MemberRole> memberRoleList = member.getMemberRoles();
//            List<ResponseMemberRoleDto> responseMemberRoleDtoList = new ArrayList<>();
//            for(MemberRole ele: memberRoleList){
//                ResponseMemberRoleDto responseMemberRoleDto = new ResponseMemberRoleDto();
//                BeanUtils.copyProperties(ele, responseMemberRoleDto);
//
//                responseMemberRoleDto.setMemberId(ele.getMember().getId());
//                responseMemberRoleDto.setMemberName(ele.getMember().getFullName());
//                responseMemberRoleDto.setRoleBOId(ele.getRoleBO().getId());
//                responseMemberRoleDto.setRoleBOName(ele.getRoleBO().getName());
//
//                responseMemberRoleDtoList.add(responseMemberRoleDto);
//            }
//            dto.setResponseMemberRoleDtoList(responseMemberRoleDtoList);
//            return Optional.of(dto);
//        }
//        return null;
//    }
//
//
//    @Override
//    @Transactional
//    public Page<ResponseMemberDto> findAll(Pageable pageable) {
//        Page<Member> accountBOs = memberRepository.findAll(pageable);
//        List<ResponseMemberDto> responseMemberDtoList = new ArrayList<>();
//        for (Member acc : accountBOs) {
//            ResponseMemberDto responseMemberDto = new ResponseMemberDto();
//            BeanUtils.copyProperties(acc, responseMemberDto);
//
//            List<MemberRole> memberRoles = acc.getMemberRoles();
//            List<ResponseMemberRoleDto> responseMemberRoleDtoList = new ArrayList<>();
//            for (MemberRole ele : memberRoles) {
//                ResponseMemberRoleDto responseMemberRoleDto = new ResponseMemberRoleDto();
//                BeanUtils.copyProperties(ele, responseMemberRoleDto);
//
//                Long memberId = ele.getMember().getId();
//                responseMemberRoleDto.setMemberId(memberId);
//                String memberName = ele.getMember().getFullName();
//                responseMemberRoleDto.setMemberName(memberName);
//                Long roleBOId = ele.getRoleBO().getId();
//                responseMemberRoleDto.setRoleBOId(roleBOId);
//
//                responseMemberRoleDtoList.add(responseMemberRoleDto);
//            }
//            responseMemberDto.setResponseMemberRoleDtoList(responseMemberRoleDtoList);
//
//            responseMemberDtoList.add(responseMemberDto);
//        }
//        return new PageImpl<>(responseMemberDtoList, pageable, accountBOs.getTotalElements());
//    }
//
//    @Override
//    @Transactional
//    public ResponseMemberDto save(RequestMemberDto requestMemberDto) {
//        ResponseMemberDto responseMemberDto = new ResponseMemberDto();
//        Member member = new Member();
//        try {
//            BeanUtils.copyProperties(requestMemberDto, member);
//            member.setStatus("UNLOCKED"); //dto khong co truong status
//            memberRepository.save(member);
//
//            BeanUtils.copyProperties(member, responseMemberDto);
//            List<MemberRole> memberRoles = member.getMemberRoles();
//            List<ResponseMemberRoleDto> responseMemberRoleDtos = new ArrayList<>();
//            for(MemberRole ele: memberRoles){
//                ResponseMemberRoleDto responseMemberRoleDto = new ResponseMemberRoleDto();
//                responseMemberRoleDto.setId(ele.getId());
//                responseMemberRoleDto.setMemberId(ele.getMember().getId());
//                responseMemberRoleDto.setMemberName(ele.getMember().getFullName());
//                responseMemberRoleDto.setRoleBOId(ele.getRoleBO().getId());
//                responseMemberRoleDto.setRoleBOName(ele.getRoleBO().getName());
//
//                responseMemberRoleDtos.add(responseMemberRoleDto);
//            }
//            responseMemberDto.setResponseMemberRoleDtoList(responseMemberRoleDtos);
//        } catch (Exception ex) {
//            System.out.println("Loi:" + ex.getCause());
//            throw new RuntimeException("Error while saving Member", ex);
//        }
//        try{
//            Long id = member.getId();
//            RequestMemberRoleDto requestMemberRoleDto = new RequestMemberRoleDto();
//            requestMemberRoleDto.setMemberId(id);
//            requestMemberRoleDto.setRoleBOId(3L);
//            MemberRole memberRole = new MemberRole();
//            BeanUtils.copyProperties(requestMemberRoleDto, memberRole);
//            Member mem = memberRepository.findById(requestMemberRoleDto.getMemberId()).orElse(null);
//            RoleBO roleBO = roleBORepository.findById(requestMemberRoleDto.getRoleBOId()).orElse(null);
//            memberRole.setMember(mem);
//            memberRole.setRoleBO(roleBO);
//            memberRoleRepository.save(memberRole);
//        } catch (Exception ex) {
//            System.out.println("Loi:" + ex.getCause());
//            throw new RuntimeException("Error while saving MemberRole", ex);
//        }
//        return responseMemberDto;
//    }
//
//    @Override
//    @Transactional
//    public ResponseMemberDto update(RequestMemberDto requestMemberDto) {
//        ResponseMemberDto responseMemberDto = new ResponseMemberDto();
//        Long memberId = requestMemberDto.getId();
//        Member member = memberRepository.findById(memberId).orElse(null);
//        if(member != null) {
//            try {
//                BeanUtils.copyProperties(requestMemberDto, member);
//                memberRepository.save(member);
//
//                BeanUtils.copyProperties(member, responseMemberDto);
//                List<MemberRole> memberRoles = member.getMemberRoles();
//                List<ResponseMemberRoleDto> responseMemberRoleDtos = new ArrayList<>();
//                for (MemberRole ele : memberRoles) {
//                    ResponseMemberRoleDto responseMemberRoleDto = new ResponseMemberRoleDto();
//                    responseMemberRoleDto.setId(ele.getId());
//                    responseMemberRoleDto.setMemberId(ele.getMember().getId());
//                    responseMemberRoleDto.setMemberName(ele.getMember().getFullName());
//                    responseMemberRoleDto.setRoleBOId(ele.getRoleBO().getId());
//                    responseMemberRoleDto.setRoleBOName(ele.getRoleBO().getName());
//
//                    responseMemberRoleDtos.add(responseMemberRoleDto);
//                }
//                responseMemberDto.setResponseMemberRoleDtoList(responseMemberRoleDtos);
//            } catch (Exception ex) {
//                System.out.println("Error: " + ex.getCause());
//                throw new RuntimeException("Error while update Member", ex);
//            }
//            return responseMemberDto;
//        }
//        return null;
//    }
//
//    @Override
//    public boolean block(Long id) {
//        if (id != null) {
//            Member member = memberRepository.findById(id).orElse(null);
//            if (member != null) {
//                if (member.getStatus().equals("UNLOCKED")) {
//                    member.setStatus("BLOCKED");
//                } else {
//                    member.setStatus("UNLOCKED");
//                }
//                memberRepository.save(member);
//                return true;
//            }
//        }
//        return false;
//    }
//    @Override
//    public boolean addImage(Long id, String imageUrl) {
//        Member member = memberRepository.findById(id).orElse(null);
//        if (member != null) {
//            member.setImage(imageUrl);
//            memberRepository.save(member);
//            return true;
//        }
//        return false;
//    }
//}