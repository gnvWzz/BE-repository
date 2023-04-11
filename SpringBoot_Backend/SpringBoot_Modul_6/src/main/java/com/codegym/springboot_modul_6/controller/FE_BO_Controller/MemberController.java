package com.codegym.springboot_modul_6.controller.FE_BO_Controller;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseMemberDto;
import com.codegym.springboot_modul_6.service.FE_BO_Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/list")
    public ResponseEntity<?> getMemberList(@PageableDefault(value = 10) Pageable pageable){
        Page<ResponseMemberDto> responseMemberDtos = memberService.findAll(pageable);
        return new ResponseEntity<>(responseMemberDtos, HttpStatus.OK);
    }

    @PostMapping("/block/{id}")
    public ResponseEntity<?> blockMember(@PathVariable Long id){
        memberService.block(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
