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

    public String isExist(String name, String value) {
        String result = "";
        switch (name) {
            case "email":
                result = accountRepository.isExistEmail(value);
                System.out.println("email result는 " + result);
                break;

            case "nickName":
                result = accountRepository.isExistNickName(value);
                System.out.println("nickName result는 " + result);
                break;

            case "phoneNumber":
                result = accountRepository.isExistPhoneNumber(value);
                System.out.println("phoneNumber result는 " + result);
                break;
        }
        return result;
    }

    public void signUp(Account account) {
        accountRepository.signUp(account);
    }

    public Account getAccount(String email, String password) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        return getAccount(account);
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
