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

    public String isExist(String name, String value) {
        String result = "";
        switch (name) {
            case "email":
                result = accountRepository.isExistEmail(value);
                System.out.println("email result는 " + result);
                break;

            case "nickname":
                result = accountRepository.isExistNickname(value);
                System.out.println("nickname result는 " + result);
                break;

            case "phoneNumber":
                result = accountRepository.isExistPhoneNumber(value);
                System.out.println("phoneNumber result는 " + result);
                break;
        }
        return result;
    }

    public void updateNickname(Account account) {

    }

    public void updatePassword(Account account) {

    }

    public void updateUserInfo(Account account) {

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
