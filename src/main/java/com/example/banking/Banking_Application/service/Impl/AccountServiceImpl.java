package com.example.banking.Banking_Application.service.Impl;

import com.example.banking.Banking_Application.dto.AccountDto;
import com.example.banking.Banking_Application.entity.Account;
import com.example.banking.Banking_Application.mapper.AccountMapper;
import com.example.banking.Banking_Application.repository.AccountRepository;
import com.example.banking.Banking_Application.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account Does not Exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto depositAmount(Long id, double amount)
    {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account Does not Exist"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdrawAmount(Long id, double amount)
    {
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not Exist"));
        if(account.getBalance() < amount)
        {
            throw new RuntimeException("Insufficient amount");
        }
        double withdraw = account.getBalance() - amount;
        account.setBalance(withdraw);

        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account Does not Exist"));

        accountRepository.deleteById(id);
    }
}
