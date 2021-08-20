package com.douzone.mehago.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mehago.service.AccountService;
import com.douzone.mehago.vo.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private AccountService accountService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Account authUser = accountService.getAccount(email, password);   
		
		if(authUser == null) {
			System.out.println("로그인 실패 in LoginInterceptor");
			request.setAttribute("email", email);
			request.setAttribute("result", "fail");
			response.sendRedirect(request.getContextPath()+"/api/account/login");
			return false;
		}
		
//		세션 처리
		System.out.println("로그인 성공 in LoginInterceptor");
		System.out.println(authUser);
		HttpSession session =  request.getSession(true);
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath());
		return false; 
	}

}
