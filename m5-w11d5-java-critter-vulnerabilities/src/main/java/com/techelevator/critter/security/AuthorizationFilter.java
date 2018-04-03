package com.techelevator.critter.security;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationFilter implements Filter {


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String sessionUser = getUserFromSession(httpRequest);
		String requestUser = getUserFromRequest(httpRequest);
		
		if (requestUser != null && !requestUser.equals(sessionUser)) {
			if (sessionUser == null) {
				redirectToLoginPage(httpRequest, httpResponse);
			} else {
				httpResponse.sendError(403);
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}
	
	@Override
	public void destroy() {

	}

	private void redirectToLoginPage(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
		
		String originalRequest = httpRequest.getRequestURL().toString();
		String queryString = httpRequest.getQueryString();
		if (queryString != null) {
			originalRequest = originalRequest + "?" + queryString;
		}
		
		String context = httpRequest.getServletContext().getContextPath();
		String redirectRequest = context + "/login?destination=" + URLEncoder.encode(originalRequest, "UTF-8");
		httpResponse.sendRedirect(redirectRequest);
	}

	private String getUserFromSession(HttpServletRequest httpRequest) {
		return (String) httpRequest.getSession().getAttribute("currentUser");
	}
	
	private String getUserFromRequest(HttpServletRequest httpRequest) {
		String user = null;
		String[] pathParts = httpRequest.getServletPath().split("/");
		if (pathParts.length >= 3) {
			if (pathParts[2].equals("new") == false) {
				user = pathParts[2];
			}
		}
		return user;
	}
	
}
