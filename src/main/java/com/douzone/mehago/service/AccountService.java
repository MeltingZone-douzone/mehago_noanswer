package com.douzone.mehago.service;

import com.douzone.mehago.repository.AccountRepository;
import com.douzone.mehago.vo.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public boolean signUp(Account account) {
        return accountRepository.insert(account);
    }

    public String existsData(String name, String value) {
        return accountRepository.existsData("phoneNumber".equals(name) ? "phone_number" : name, value);
    }

    public void updateNickname(Account account) {

    }

    public void updatePassword(Account account) {

    }

    public void updateUserInfo(Account account) {
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

    public Account getAccount(Account account) {
        return accountRepository.getAccount(account);
    }  

    public boolean updateToken(Account account) {
        return accountRepository.updateToken(account);
    }

    public Account getAccountByToken(Account account) {
        return accountRepository.getAccountByToken(account);
    }
}
