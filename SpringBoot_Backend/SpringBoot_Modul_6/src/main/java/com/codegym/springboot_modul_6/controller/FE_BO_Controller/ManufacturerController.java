package com.codegym.springboot_modul_6.controller.FE_BO_Controller;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestManufacturerDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseManufacturerDto;
import com.codegym.springboot_modul_6.service.FE_BO_Service.impl.ManufacturerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/manufacturer")
public class ManufacturerController {
    @Autowired
    private ManufacturerServiceImpl manufacturerService;

    @GetMapping("/list")
    public ResponseEntity<?> getManufacturerList(@PageableDefault(value = 10) Pageable pageable){
        Page<ResponseManufacturerDto> responseManufacturerDtos = manufacturerService.findAll(pageable);
        return new ResponseEntity<>(responseManufacturerDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getManufacturerById(@PathVariable Long id){
        Optional<ResponseManufacturerDto> responseManufacturerDto = manufacturerService.findById(id);
        return new ResponseEntity<>(responseManufacturerDto.get(), HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveManufacturer(@RequestBody RequestManufacturerDto requestManufacturerDto){
        manufacturerService.save(requestManufacturerDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/block/{id}")
    public ResponseEntity<?> blockManufacturer(@PathVariable Long id){
        manufacturerService.block(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}


