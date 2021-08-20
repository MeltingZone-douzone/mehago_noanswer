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

    public String isExist(String name, String value) {
        String result = "";
        switch (name) {
            case "email":
                result = accountRepository.isExistEmail(value);
                System.out.println("email result는 "+ result);
            break;

            case "nickName":
                result = accountRepository.isExistNickName(value);
                System.out.println("nickName result는 "+ result);
            break;

            case "phoneNumber":
                result = accountRepository.isExistPhoneNumber(value);
                System.out.println("phoneNumber result는 "+ result);
            break;
        }
        return result;
    }
}
