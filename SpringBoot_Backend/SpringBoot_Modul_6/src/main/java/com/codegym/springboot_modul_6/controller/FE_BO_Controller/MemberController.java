//package com.codegym.springboot_modul_6.controller.FE_BO_Controller;
//
//import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestManufacturerDto;
//import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestMemberDto;
//import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseManufacturerDto;
//import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseMemberDto;
//import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.Member;
//import com.codegym.springboot_modul_6.service.FE_BO_Service.MemberService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping("/member")
//public class MemberController {
//    @Autowired
//    private MemberService memberService;
//
//    @GetMapping("/list")
//    public ResponseEntity<?> getMemberList(@PageableDefault(value = 10) Pageable pageable){
//        Page<ResponseMemberDto> responseMemberDtos = memberService.findAll(pageable);
//        return new ResponseEntity<>(responseMemberDtos, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getMemberById(@PathVariable Long id){
//        ResponseMemberDto responseMemberDto = memberService.findById(id).orElse(null);
//        if(responseMemberDto != null) {
//            return new ResponseEntity<>(responseMemberDto, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }
//    @PostMapping("/save")
//    public ResponseEntity<?> saveMember(@RequestBody RequestMemberDto requestMemberDto){
//        ResponseMemberDto responseMemberDto = memberService.save(requestMemberDto);
//        return new ResponseEntity<>(responseMemberDto, HttpStatus.OK);
//    }
//
//    @PostMapping("/update/{id}")
//    public ResponseEntity<?> updateMember(@RequestBody RequestMemberDto requestMemberDto){
//        ResponseMemberDto responseMemberDto = memberService.update(requestMemberDto);
//        if (responseMemberDto != null) {
//            return new ResponseEntity<>(responseMemberDto, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//    }
//
//    @PostMapping("/block/{id}")
//    public ResponseEntity<?> blockMember(@PathVariable Long id){
//        memberService.block(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    @PostMapping("/add-image/{id}")
//    public ResponseEntity<?> addImage(@PathVariable Long id, @RequestBody String imageUrl) {
//        boolean response = memberService.addImage(id, imageUrl);
//        if (response){
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//}
