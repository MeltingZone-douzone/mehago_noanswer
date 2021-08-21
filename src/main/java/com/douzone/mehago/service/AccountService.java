package com.douzone.mehago.service;

import com.douzone.mehago.repository.AccountRepository;
import com.douzone.mehago.vo.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void updateNickname(Account account) {

        
    }

    
    public void updatePassword(Account account) {
        
        
        
    }

    public void updateUserInfo(Account account) {
        
        
    }


    public void signUp(Account account) {
        accountRepository.signUp(account);
    }

    public Account getAccount(Account account){
        return accountRepository.getAccount(account);
    }


    public boolean updateToken(Account account) {
        return accountRepository.updateToken(account);
    }  

    public Account getAccountByToken(Account account){
        return accountRepository.getAccountByToken(account);
    }
}
