package com.example.banking.Banking_Application.repository;

import com.example.banking.Banking_Application.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
