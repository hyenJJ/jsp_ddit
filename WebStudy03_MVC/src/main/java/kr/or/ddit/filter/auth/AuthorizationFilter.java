package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tiles.request.ApplicationAccess;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;
// 3
/**
 * 
 * 보호자원에 대한 요청에서 인증된 사용자가 해당 자원에 대한 권한을 소유하고 있는지 확인하는 필터.
 *
 */
@Slf4j
public class AuthorizationFilter implements Filter{

	private Map<String, String[]> securedResources;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		String uri = req.getServletPath();
		
		if(securedResources==null) {
			securedResources = 
					(Map<String, String[]>)request.getServletContext().getAttribute(AuthencationFilter.ATTRNAME);
		}
		// 인증 여부를 확인 안하는 이유는 이미 앞에서 하고 왔기 때문이다
		// 등급에 대한 소유를 확인하는것은
		// 클라이언트에게 부여되는 역할이 있다는 것
		// 자원에 설정되어있는 역할이 있다는 것 
		
		
		//보호자원 여부
		//여:
		//	인증객체 확보
		// 	허가 여부(사용자의 role, 자원에 설정된 role 비교)
		//	여 : 통과 및 해당 자원 서비스 
		//	부 : 403 에러 응답 -> 이때는 이미 로그인이 되어있는 상태이기 때문에 로그인 form으로 가지 않음 
		//부 : 
		
		
		
		String[] roles = securedResources.get(uri);
//		HttpSession session = req.getSession();
//		MemberVO member = (MemberVO)session.getAttribute("authMember");
		
		
		boolean pass = false;
		
		if(roles == null) {
			pass=true;
			
		}else {
			MemberVO authMember = ((MemberVOWrapper)req.getUserPrincipal()).getRealUser();
			
			if(Arrays.binarySearch(roles, authMember.getMemRole())<0) {
				pass = false;
			}else {
				pass = true;
				
			}

		}
		if(pass) {
			chain.doFilter(request, response);
		}else {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
