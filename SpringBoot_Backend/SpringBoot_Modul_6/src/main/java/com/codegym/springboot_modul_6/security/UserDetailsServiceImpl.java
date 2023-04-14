//package com.codegym.springboot_modul_6.security;
//import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Account;
//import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.UserPrinciple;
//import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IAccountRepository;
//import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import javax.transaction.Transactional;
//
//
//@Service
//@Transactional
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private IUserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Account account = userRepository.findByUsername(username).get();
//
//        if (account == null) {
//            throw new UsernameNotFoundException("User " + username + "was not found in database!");
//        }
//
//        return UserPrinciple.build(account);
//    }
//}
