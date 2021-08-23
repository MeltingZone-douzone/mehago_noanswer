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

    public String existsData(String name, String value) {
        Map<String, Object> map = new HashMap();
		map.put("name", name);
		map.put("value", value);
        System.out.println(map);
        return sqlSession.selectOne("account.existData", map);
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
        return sqlSession.selectOne("account.findByEmailAndPassword", account);
    }

    public boolean updateToken(Account account) {
        return sqlSession.update("account.updateToken", account) == 1 ? true : false;
    }

    public Account getAccountByToken(Account account) {
        return sqlSession.selectOne("account.findByNo", account);
    }
}
