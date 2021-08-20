package com.example.serverconstruction.repository;

import com.example.serverconstruction.vo.Account;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {
    
    @Autowired
    private SqlSession sqlSession;

    public Boolean insert(Account vo) {
		int result = sqlSession.insert("account.insert", vo);
		return result == 1;
	  }

    public String isExistEmail(String email) {
        return sqlSession.selectOne("account.isExistEmail",email);
    }


    public String isExistNickName(String nickName) {
        return sqlSession.selectOne("account.isExistNickName", nickName);
    }


    public String isExistPhoneNumber(String phoneNumber) {
        return sqlSession.selectOne("account.isExistPhoneNumber",phoneNumber);
    }

}
