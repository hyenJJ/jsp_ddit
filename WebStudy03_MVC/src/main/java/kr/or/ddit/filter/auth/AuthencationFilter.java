package kr.or.ddit.filter.auth;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
// 2
/**
 * 보호자원에 대한 요청에 대해 인증 여부를 판단하는 필터.
 * 
 *
 */
@Slf4j
public class AuthencationFilter implements Filter {
	
	
	public static final String ATTRNAME = "securedResources";
	
	private Map<String, String[]> securedResources;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		securedResources = new LinkedHashMap<>();
		ServletContext application = filterConfig.getServletContext();
		application.setAttribute(ATTRNAME, securedResources);
		
		Properties props = new Properties();


		try(
				InputStream is = AuthencationFilter.class.getResourceAsStream("/kr/or/ddit/SecuredResources.properties");
		){
			 props.load(is);
			 
			 Enumeration<Object> keys = props.keys();
			 
			 while (keys.hasMoreElements()) {
				String uri = keys.nextElement().toString();
				String roles = props.getProperty(uri);
				String[] roleArray = roles.split(",");
				Arrays.sort(roleArray);
				
				securedResources.put(uri.trim(),roleArray);
				
				log.info("보호자원 - {} : {}", uri.trim(), roles.split(","));
			}
			 
		} catch (IOException e) {
			
			throw new ServletException(e);
		}
		
	
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//1 . 보호 자원 여부 판단
		// 여 : 
		// 		인증여부
		//		여 : 통과 및 해당 자원 서비스
		//		부 : 로그인이 안되어있는데 보호자원을 요청 -> 로그인 페이지로 이동 
		// 부 : 통과 및 해당 자원 서비스
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getServletPath();
		
	
		boolean pass = true;
		if(securedResources.containsKey(uri) && req.getUserPrincipal() == null) {
			pass = false;
		}
		
		if(pass) {
			chain.doFilter(req, response);
			
		}else {
			resp.sendRedirect(req.getContextPath()+"/login/loginForm.jsp");
			
		}
		
			
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
