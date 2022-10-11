package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FirstFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig ) throws ServletException {
		log.info("{} 필터 초기화.", this.getClass().getSimpleName());
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// request filtering
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		log.info("request filtering in  {}", this.getClass().getSimpleName());
		chain.doFilter(request, response);
		
		
		// response filtering
		log.info("response filtering in {}", this.getClass().getSimpleName());
		
		
	}

	@Override
	public void destroy() {
		
		
	}

}
