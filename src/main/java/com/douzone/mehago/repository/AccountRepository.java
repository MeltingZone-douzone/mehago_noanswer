package com.douzone.mehago.repository;

import com.douzone.mehago.vo.Account;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccountRepository {
 
    
    private final SqlSession sqlSession;

    public boolean updateNickname(Account account) {
        return sqlSession.update("account.updateNickname", account.getNickname()) == 1 ? true : false;
    }

    public boolean updatePassword(Account account) {
        return sqlSession.update("account.updatePassword", account.getNickname()) == 1 ? true : false;
    }

    public boolean updateUserInfo(Account account) {
        return sqlSession.update("account.updateUserInfo", account.getNickname()) == 1 ? true : false;
    }

    public void signUp(Account account) {
        sqlSession.insert("account.insert", account);

    }

}
