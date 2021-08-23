package com.douzone.mehago.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.douzone.mehago.vo.Account;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		System.out.println("AuthUserHandlerMethodArgumentResolver called");

		if (supportsParameter(parameter) == false) { // 지원안하는 파라미터이면
			return WebArgumentResolver.UNRESOLVED; // 사용자 요청이 Controller에 도달하기 전에 그 요청의 파라미터들을 수정할 수 있도록 해줌
		}

		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest(); // 통해 클라이언트 요청이 담긴 파라미터를 컨트롤러보다
																							// 먼저 받아서 작업을 수행할 수 있다.
		HttpSession session = request.getSession();
		if (session == null) {
			return null;
		}
		return session.getAttribute("authUser"); // return해주는 타입이 argument로 박힘 authUser
	}

	// 지원해주는 타입인가 판단함. ex) 어노테이션이 붙어있는 파라미터인지, type이 UserVO인가
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		if (authUser == null) { // @authUser가 선언되어있지 않으면,
			return false;
		}

		// @authUser가 선언되어있는데 타입이 UserVO가 아니면
		if (parameter.getParameterType().equals(Account.class) == false) {
			return false;
		}

		return true;
	}

}
