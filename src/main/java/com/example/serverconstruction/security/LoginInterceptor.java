package com.example.serverconstruction.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.serverconstruction.service.AccountService;
import com.example.serverconstruction.vo.Account;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private AccountService accountService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Account authUser = AccountService.getUser(email, password);   
		
		if(authUser == null) {
			System.out.println("로그인 실패 in LoginInterceptor");
			request.setAttribute("email", email);
			request.setAttribute("result", "fail");
			request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
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
