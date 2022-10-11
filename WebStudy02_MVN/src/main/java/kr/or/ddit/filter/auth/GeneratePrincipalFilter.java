package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;


//1
@Slf4j
public class GeneratePrincipalFilter implements Filter {


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	log.info("{} 필터 초기화", this.getClass().getSimpleName());
	
}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		MemberVO authMember = (MemberVO)req.getSession().getAttribute("authMember");
		
		if(authMember != null) { 
		HttpServletRequestWrapper wrapperReq = new HttpServletRequestWrapper(req) {
			// 익명객체 정의
			
			@Override  // 누군가가 현재 로그인이 되어있는 상태 일 때 (authMember 가 있을 때 )
			public Principal getUserPrincipal() {
				MemberVOWrapper principal = new MemberVOWrapper(authMember);
																//adaptee
				
				return principal;
			}
			
		};
		
			chain.doFilter(wrapperReq, response);
		}else {
			
			chain.doFilter(request, response);
		}
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	
}
