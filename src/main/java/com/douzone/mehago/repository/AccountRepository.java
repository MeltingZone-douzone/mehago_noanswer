package com.douzone.mehago.repository;


import java.util.HashMap;
import java.util.Map;

import com.douzone.mehago.vo.Account;

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

    public void signUp(Account account) {
      sqlSession.insert("account.insert", account);

    }
    public Account getAccount(Account account) {
      return sqlSession.selectOne("account.findByEmailAndPassword",account);
    }

    public Account searchAccount(String name, String email){
      Map<String, String> map = new HashMap<>();
		  map.put("name", name);
		  map.put("email", email);
      return sqlSession.selectOne("account.searchAccount", map);
    }

    public Account searchEmail(String name, String phoneNumber){
      Map<String, String> map = new HashMap<>();
		  map.put("name", name);
		  map.put("phoneNumber", phoneNumber);
      return sqlSession.selectOne("account.searchEmail",map);
    }

    public void updateRendomPassword(String randomPassword, String email){
      Map<String, String> map = new HashMap<>();
		  map.put("randomPassword", randomPassword);
		  map.put("email", email);
      sqlSession.update("account.updateRendomPassword", map);
    }

    public Account findByEmailAndPassword(String email, String password) {
        Map<String, String> map = new HashMap<>();
		map.put("e", email);
		map.put("p", password);
        return sqlSession.selectOne("account.findByEmailAndPassword", map);
    }

}
