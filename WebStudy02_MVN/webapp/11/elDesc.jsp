<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>EL( Expression Language : 표현 언어)</h4>
<pre>
	: 표현식의 대체로 Scope 통해 공유되고 있는 속성(attribute)데이터를 출력하기 위해 사용.( = 일반변수는 사용하지 않는다)
	제어문의 형태를 갖고있지 않다(if,for 문) 그냥 문자 출력 하는게 목적
	Model 1 에서는 사용하지 않는다 
	메소드 호출 불가
	${test} -> 장점 - 존재하지 않는 속성을 가져오더라도 오류 안나고 null 도 안보임 
	
	<%
		request.setAttribute("sample", "   ");
		session.setAttribute("sample", "세션속성");
		pageContext.setAttribute("list", Arrays.asList(""));
	%>
	
	${sample }, <%=request.getAttribute("\"sample") %>
	-> Scope를  직접적으로 정해주지 않았기때문에 모든 세션을 돌아다님 (작은 공간부터)
	
	${sessionScope.sample }, <%=request.getAttribute("sample") %>
	-> 직접 Scope를 정해주고 싶으면 앞에 session(원하는 scope)Scope. 을 붙여주면 됨
	
	1. EL 연산자
		산술연산자 : ${3+4 }, ${num+2 } , ${"3"+4 }, \${"a"+4 }
				어떤 경우에도 + 연산만 한다 (문자 출력이 목적이기 때문에)
				{"a"+4 } -> 하지만 "문자" 인경우 숫자로 파싱을 할 수 가 없기 때문에 500 에러가 뜸
					${num-2 }, ${3/4 }
					데이터 타입은 연산이 실행될 때 정해진다
					연산자가 main 
					피연산자가 연산자에 의해 결정이 됨 
		
		논리 연산자 : ${true and true }, ${alpha or false} , ${not alpah }
					논리 연산자 기호보다는 키워드가 더 많이 사용됨
					
					not alpah -> 데이터가 존재하지 않아서 false 였다가 not연산자가 붙어서 true로 바뀜
		비교 연산자 : eq, ne, gt, lt, ge, le 
				  (==, !=,  >, <,  >=, <= )
					${ 3 le 4}, ${2 eq 2 }, <%-- ${3 ne 2 } --%> 
					
		단항 연산자 : empty  (++, -- => 원래 지원 안되는데 최신 버전은 지원이 된다 그리고 우리나라는 아직 3.대를 쓰고 있음)
				${empty alpha }, ${empty sample } ( 1.null이냐 아니냐 2.string 이면 길이를 checking)
				empty List : ${empty list } -> list에 값이 없다면 사이즈가 0이기 때문에 null
											-> "" 이렇게 공백만 줘도 값 상관없이 사이즈가 생기기때문에 false
										
		삼항 연산자 : (조건식) ? (참 표현) : (거짓 표현)
					${empty alpha ? "비어있음" : "채워져있음" }
					${not empty list ? "채워져있음" : "비어있음"  }
					
	2. EL 에서 객체 사용
		<%
			MemberVO member = new MemberVO();
			member.setMemName("김은대");
			pageContext.setAttribute("member", member);
		%>
		${member.memName }, ${member ['memName']} , ${member.getMemName()}
		${member.memTest }, ${member ['memTest']} 
		눈에는 property에서 가져오는 것처럼 보이지 만 getter를 이용해서 가져온 것임		
		
	3. EL 에서 컬렉션 사용
	4. EL 기본 객체
		scope 객체 : pageScope(Map), requestScope(Map), sessionScope(Map), applicationScope(Map)
				${sessionScope.sample } , ${sessionScope['sample'] }
				
		request parameter 객체 : param(Map&gt;String,String&lt;), paramValues(Map&gt;String,String[]&lt;)
			<%=request.getParameter("param1") %> 
			${param.param1 }, ${param['param1'] }
			<%=request.getParameterValues("param1") %>
			
			${paramValues.param1[1] } , ${paramValues['param1'][1]} -> 인덱스사용해서 원하는 순서 값 가져올 수 있음
		request header 객체 : header(Map&gt;String,String&lt;), headerValues(Map&gt;String,String[]&lt;)
			<%=request.getHeader("Accept") %>
			${header.accept } , ${header['accept']} -> header는 대소문자를 구분 하지 않는다 
			<%=request.getHeader("user-agent") %>
			${header.user-agent } , ${header['user-agent']}
			->-가 main이 되어서 0
			
		cookie 객체 : cookie (클라이언트에 저장되어 있는 cookie)
			<%=request.getCookies() %>
			${cookie.JSESSIONID }, ${cookie.JSESSIONID.value }
			${cookie['JSESSIONID'] }, ${cookie['JSESSIONID']['value'] }
		pageContext : ${pageContext} 
			<%=session.getId() %>, ${pageContext.session.id }, ${pageContext['session']['id'] }
			->  ${sission } 안됨
</pre>	
<script>
	let sample = "스크립트변수";
	alert(`변수의 값 : \${sample}`)// \를 붙여줌으로써 문자로 설정 
</script>

</body>
</html>