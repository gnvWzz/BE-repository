//
//package com.codegym.springboot_modul_6.service.FE_SF_Service;
//
//
//import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Account;
//import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.UserPrinciple;
//import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IAccountRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.Optional;
//
//@Service
//public class AccountService implements IAccountService{
//    @Autowired
//    private IAccountRepository iAccountRepository;
//    @Override
//    public Iterable<Account> findAll() {
//        return iAccountRepository.findAll();
//    }
//
//    @Override
//    public Optional<Account> findById(Long id) {
//        return iAccountRepository.findById(id);
//    }
//
//
//
//    @Override
//    public void save(Account account) {
//        account.setPassword(BCrypt.hashpw(account.getPassword(),BCrypt.gensalt(10)));
//        iAccountRepository.save(account);
//    }
//
//    @Override
//    public void remove(Long id) {
//
//    }
//
//    @Override
//    public Optional<Account> findAccountByUsername(String username) {
//        return iAccountRepository.findByUsername(username);
//    }
//
//    @Override
//    public Optional<Account> findAccountByUEmail(String username) {
//        return iAccountRepository.findByEmail(username);
//    }
//
//    @Override
//    public boolean checkLogin(String username, String password) {
//            Optional<Account> account = iAccountRepository.findByUsername(username);
//            String passwordDb =  account.get().getPassword();
//            if (account.isPresent()){
//                if (BCrypt.checkpw(password, passwordDb)){
//                    return true;
//
//                }
//            }
//        return false;
//    }
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Account> account  = iAccountRepository.findByUsername(username);
//        if (!account.isPresent()){
//        throw new UsernameNotFoundException(username);
//        }
//
//        return UserPrinciple.build(account.get());
//    }
//
//
//}
