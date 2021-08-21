package com.douzone.mehago.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mehago.service.AccountService;
import com.douzone.mehago.utils.JsonBodyConverter;
import com.douzone.mehago.vo.Account;

import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;


public class LoginInterceptor implements HandlerInterceptor{
	
	@Autowired
	private AccountService accountService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println(JsonBodyConverter.getBody(request));
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println(email);
		System.out.println(password);

		Account authUser = accountService.getAccount(email, password);   

		if(authUser == null) { // 로그인 실패시
			System.out.println("fail to login in LoginInterceptor");
			throw new InvalidInputException("fail to login");
		
		}
		
        System.out.println("LoginInterceptor");
        return true;
	}

}