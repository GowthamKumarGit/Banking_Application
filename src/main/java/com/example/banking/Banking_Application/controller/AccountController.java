package com.example.banking.Banking_Application.controller;

import com.example.banking.Banking_Application.dto.AccountDto;
import com.example.banking.Banking_Application.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    //Add Account Rest API
    @PostMapping("/addaccount")
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //Get Account By ID
    @GetMapping("/getAccountById/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id)
    {
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/depositAmount/{id}/{amount}")
    public ResponseEntity<AccountDto> depositAmount(@PathVariable Long id, @PathVariable double amount)
    {
        AccountDto accountDto = accountService.depositAmount(id,amount);
        return ResponseEntity.ok(accountDto);
    }


    @PutMapping("/withdrawAmount/{id}/{amount}")
    public ResponseEntity<AccountDto> withdrawAmount(@PathVariable Long id, @PathVariable double amount)
    {
        AccountDto accountDto = accountService.withdrawAmount(id,amount);
        return ResponseEntity.ok(accountDto);
    }


    @GetMapping("/getAllAccounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts()
    {
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @DeleteMapping("/deleteAccountById/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id)
    {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully!");
    }
}
