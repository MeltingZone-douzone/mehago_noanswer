package com.douzone.mehago.service;

import com.douzone.mehago.repository.AccountRepository;
import com.douzone.mehago.vo.Account;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {


    private final AccountRepository accountRepository;

    public void updateNickname(Account account) {

        
    }

    
    public void updatePassword(Account account) {
        
        
        
    }

    public void updateUserInfo(Account account) {
        
        
    }


    public void signUp(Account account) {
        accountRepository.signUp(account);
    }
    
    public Account login(Account account){
        return accountRepository.login(account);
    }  
}
