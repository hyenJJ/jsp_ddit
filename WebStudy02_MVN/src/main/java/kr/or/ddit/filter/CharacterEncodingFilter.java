package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

//인코딩할 때 여기만 바뀜으로 인해 결합력이 낮아짐 
//@WebFilter("/*")  
//web.xml에 등록하고 mapping을 해줌 
//근데 web에 써주는 이유는 순서를 정할수 없기 때문에 권장하지 않는다 
public class CharacterEncodingFilter implements Filter {

	private String encoding;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		this.encoding = filterConfig.getInitParameter("encoding");
		
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		req.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
