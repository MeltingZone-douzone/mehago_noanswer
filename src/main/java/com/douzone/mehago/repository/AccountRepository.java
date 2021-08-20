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
    
    public Account findByEmailAndPassword(String email, String password) {
        Map<String, Object> map = new HashMap();
		map.put("email", email);
		map.put("password", password);
		return sqlSession.selectOne("account.findByEmailAndPassword", map);
    }

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
