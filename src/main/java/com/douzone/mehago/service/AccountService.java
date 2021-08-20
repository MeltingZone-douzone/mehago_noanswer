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

    public Account searchAccount(String name, String email){
        return accountRepository.searchAccount(name, email);
    }

    public Account searchEmail(String name, String phoneNumber){
        return accountRepository.searchEmail(name, phoneNumber);
    }

    public void changeRandomPassword(String randomPassword, String email){
        accountRepository.updateRendomPassword(randomPassword, email);
    }


    public Account getAccount(String email, String password) {
        return accountRepository.findByEmailAndPassword(email, password);
    }

}
