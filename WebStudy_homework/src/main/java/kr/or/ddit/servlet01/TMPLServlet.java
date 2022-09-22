package kr.or.ddit.servlet01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.tmpl")
public class TMPLServlet extends HttpServlet{
  
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		
		try {
		StringBuffer tmplSource = readTemplate(req);
		
		
		String html = evaluateVariable(req, tmplSource);
		
		sendResponse(resp, html);
		}catch (FileNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
		}
	}	
	private final String REGEX = "#(\\w+)#";
	
	private String evaluateVariable(HttpServletRequest req, StringBuffer tmplSource) {
		Pattern pattern = Pattern.compile(REGEX);
		
		StringBuffer htmlSource = new StringBuffer();
		Matcher matcher = pattern.matcher(tmplSource);
		while(matcher.find()) {
			String varName = matcher.group(1);
			Object value = req.getAttribute(varName);
		    matcher.appendReplacement(htmlSource, Objects.toString(value, ""));
		}
		matcher.appendTail(htmlSource);
		return htmlSource.toString();
	}

	private void sendResponse(HttpServletResponse resp, String contents) throws IOException {
	   resp.setContentType("text/html;charset=UTF-8");
		try(
	PrintWriter out = resp.getWriter();
		){
		out.println(contents);
	}
	

	}
	
	
	private StringBuffer readTemplate(HttpServletRequest req) throws IOException {
		String servletPath = req.getServletPath();
		
		InputStream is = getServletContext().getResourceAsStream(servletPath);
		if(is==null) {
			throw new FileNotFoundException(String.format("%s 해당 파일이 없음.",servletPath));
			         //IOException의 자식
		}
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader reader = new BufferedReader(isr);
		
		String temp = null;
		
		StringBuffer tmplSource = new StringBuffer();
		while((temp = reader.readLine()) != null) {
			tmplSource.append(temp + "\n");
		}
		return tmplSource;

	}
	
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String ServletPath = req.getServletPath();
//		
//		InputStream is = getServletContext().getResourceAsStream(ServletPath);
//	    InputStreamReader isr = new InputStreamReader(is);
//	    BufferedReader reader = new BufferedReader(isr);
//	    
//	    String temp = null;
//	    while( (temp = reader.readLine()) != null) {
//	    	System.out.println(temp);
//	    }
//	}
	
}
