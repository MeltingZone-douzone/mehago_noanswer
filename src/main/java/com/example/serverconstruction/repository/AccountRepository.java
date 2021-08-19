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


    public Boolean existsByEmail(String email) {
      return sqlSession.selectOne("account.findByEmail",email);
    }

}
