package com.douzone.mehago.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mehago.service.AccountService;
import com.douzone.mehago.utils.JwtTokenUtil;
import com.douzone.mehago.vo.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private AccountService accountService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();

		Account account = new Account();
		account.setEmail(body.split("\"")[3]);
		account.setPassword(body.split("\"")[7]);
		Account result = accountService.getAccount(account);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		if (result == null) {
			response.getWriter().write("cant find Account");
			return false;
		}
		String token = jwtTokenUtil.generateAccessToken(result);
		account.setToken(token);

		accountService.updateToken(account);
		response.getWriter().write(token);
		return false;
	}
}