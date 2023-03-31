package com.codegym.springboot_modul_6.Repository.FE_SF_Repository;

import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account,Long> {
}
