package com.douzone.mehago.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

<<<<<<< HEAD
=======
import com.douzone.mehago.service.AccountService;
import com.douzone.mehago.utils.JwtDecoder;
import com.douzone.mehago.vo.Account;

import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> origin/sewon
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogoutInterceptor extends HandlerInterceptorAdapter {
	
<<<<<<< HEAD
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		// TODO 세션 ㄴ!
		HttpSession session = request.getSession(); // default가 false라서 비워놓아도 됨
		session.removeAttribute("authUser");
		session.invalidate();


		System.out.println("로그아웃 성공 in LogoutInterceptor");
		response.sendRedirect(request.getContextPath());
=======
	@Autowired
	private AccountService accountService;
	@Autowired
	private JwtDecoder jwtDecoder;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
				
							
			if(request.getHeader("Authorization") == null ){
				response.getWriter().write("cant find Account");   
				return false;   
			}

//			token이 존재
			String token = request.getHeader("Authorization");
				Account decodeAccount = jwtDecoder.decodeJwt(token.split("Bearer ")[1]);
				decodeAccount.setToken(null);
				System.out.println(decodeAccount.toString());				
				accountService.updateToken(decodeAccount);

				

		System.out.println("로그아웃 성공 in LogoutInterceptor");
		response.getWriter().write("success");   
>>>>>>> origin/sewon
		return false;
	}

}
<<<<<<< HEAD

=======
>>>>>>> origin/sewon
