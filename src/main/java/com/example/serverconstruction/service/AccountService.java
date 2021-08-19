package com.example.serverconstruction.service;

import com.example.serverconstruction.repository.AccountRepository;
import com.example.serverconstruction.vo.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;

    public boolean signUp(Account account) {
        return accountRepository.insert(account);
    }
}
