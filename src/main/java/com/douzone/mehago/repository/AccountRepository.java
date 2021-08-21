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
    public Account getAccount(Account account) {
        return sqlSession.selectOne("account.findByEmailAndPassword",account);
    }

}
