package com.codegym.springboot_modul_6.Service.FE_SF_Service;

import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.Login;
import com.codegym.springboot_modul_6.Repository.FE_SF_Repository.ILoginRepository;
import com.codegym.springboot_modul_6.Service.FE_SF_Service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements ILoginService {

    @Autowired
    ILoginRepository loginRepository;
    @Override
    public Iterable<Login> findAll() {
        return loginRepository.findAll();
    }

    @Override
    public Optional<Login> findById(Long id) {
        return loginRepository.findById(id);
    }

    @Override
    public void save(Login login) {
        loginRepository.save(login);
    }

    @Override
    public void remove(Long id) {

    }
}
