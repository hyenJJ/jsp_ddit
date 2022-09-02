 package kr.or.ddit.servlet01;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet; 
 
@WebServlet(value="/imageForm", loadOnStartup = 1)

public class ImageFormServlet_version3 extends HttpServlet{	
	private ServletContext application;
	private String imageFolderPath;
	
	@Override
	public void init(ServletConfig config) throws ServletException{	 
	 super.init(config);
	 application = getServletContext();
	
	 imageFolderPath = application.getInitParameter("imageFolderPath");
	 
		 
 }	
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException{
	
	 File imageFolder = new File(imageFolderPath);
	 String[] children = imageFolder.list((file, name)->{
    	 String mime = application.getMimeType(name);
    	 return mime != null && mime.startsWith("image");
    	 
     });
     
	 
	 String pattern = "<option>%s</option>\n";
	 StringBuffer options = new StringBuffer();
		 for(String image : children) {
			 options.append(String.format(pattern, image));
		 }
		 
		 req.setAttribute("cPath", req.getContextPath());
		 req.setAttribute("options", options);
		 
		 req.getRequestDispatcher("/02/imageForm.jsp").forward(req, resp);

			/*
			 * StringBuffer html = new StringBuffer();
			 * 
			 * resp.setContentType("text/html;charset=UTF-8"); PrintWriter out = null; try {
			 * 
			 * out = resp.getWriter(); out.println(html); }finally { if(out!=null)
			 * out.close();
			 * 
			 * }
			 */
	}

}

