package com.douzone.mehago.repository;

import java.util.HashMap;
import java.util.Map;

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
