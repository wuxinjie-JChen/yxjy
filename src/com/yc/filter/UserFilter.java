package com.yc.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns="*.jsp")
public class UserFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		HttpSession session=req.getSession();
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out=res.getWriter();
		String uri=req.getRequestURI();
		if("/YXJY/login.jsp".equals(uri)){
			chain.doFilter(request, response);
		}else{
			if(session.getAttribute("student")==null&&session.getAttribute("teacher")==null&&session.getAttribute("admin")==null){
				out.println("<script>location.href='../login.jsp';alert('您还未登录，请登录！');</script>");
			}else{
				chain.doFilter(request, response);
			}
		}
		
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
