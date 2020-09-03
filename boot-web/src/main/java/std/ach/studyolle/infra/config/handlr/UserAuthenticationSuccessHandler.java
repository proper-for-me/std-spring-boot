package std.ach.studyolle.infra.config.handlr;

/*
 * Copyright (c) 2014 SK telecom.
 * All right reserved.
 *
 * This software is the confidential and proprietary information of SK telecom.
 * You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with SK telecom.
 */


import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;


import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 인증 성공시 응답 규격을 처리하는 핸들러 클래스.
 * </p>
 *
 * <ul>
 * <li>Created on : 2020. 3. 13.</li>
 * <li>Created by : sptek_hskim</li>
 * </ul>
 */
@Slf4j
public class UserAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
		implements AuthenticationSuccessHandler {

	private String url;

	public UserAuthenticationSuccessHandler(String url) {
		// TODO Auto-generated constructor stub
		this.url = url;
		super.setDefaultTargetUrl(url);
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// authentication.setAuthenticated(true);
		setDefaultTargetUrl("/");

		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		if (!authentication.isAuthenticated()) {
			throw new BadCredentialsException("Unable to authenticate");
		}

		log.debug("##### UserAuthenticationSuccessHandler - Login Succeed.: {}",
				authentication.getDetails().toString());
		Object ob = SecurityContextHolder.getContext().getAuthentication();
		// Security가 요청을 가로챈 경우 사용자가 원래 요청했던 URI 정보를 저장한 객체
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		// IP, 세션 ID
		WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();
		System.out.println("IP : " + web.getRemoteAddress());
		System.out.println("Session ID : " + web.getSessionId());

		// 인증 ID
		System.out.println("name : " + authentication.getName());

		// 권한 리스트
		List<GrantedAuthority> authList = (List<GrantedAuthority>) authentication.getAuthorities();
		System.out.print("권한 : ");
		for (int i = 0; i < authList.size(); i++) {
			System.out.print(authList.get(i).getAuthority() + " ");
		}
		System.out.println();
		String uri = "";
		// 있을 경우 URI 등 정보를 가져와서 사용
		if (savedRequest != null) {
			uri = savedRequest.getRedirectUrl();

			// 세션에 저장된 객체를 다 사용한 뒤에는 지워줘서 메모리 누수 방지
			requestCache.removeRequest(request, response);

			System.out.println(uri);
		}

		// 세션 Attribute 확인
		Enumeration<String> list = request.getSession().getAttributeNames();
		while (list.hasMoreElements()) {
			System.out.println(list.nextElement());
		}

		// super.onAuthenticationSuccess(request, response, authentication);

		// super.onAuthenticationSuccess(request, response, authentication);
		// response.sendRedirect(uri);

		// getRedirectStrategy().sendRedirect(request, response, "/");

		// HttpSession session = request.getSession();
		// if (session != null) {
		// String redirectUrl = (String) session.getAttribute("prevPage");
		// if (redirectUrl != null) {
		// // we do not forget to clean this attribute from session
		// session.removeAttribute("prevPage");
		// // then we redirect
		// redirectUrl = "/auth/login.do";
		// getRedirectStrategy().sendRedirect(request, response, redirectUrl);
		// } else {
		// super.onAuthenticationSuccess(request, response, authentication);
		// }
		// } else {
		// super.onAuthenticationSuccess(request, response, authentication);
		// }

		response.sendRedirect("/");
		// response.sendRedirect("/auth/login.do");
	}
}
