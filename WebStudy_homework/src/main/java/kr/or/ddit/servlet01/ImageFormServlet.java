package kr.or.ddit.servlet01;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


//@WebServlet(value = "/imageForm", loadOnStartup = 1)
public class ImageFormServlet extends HttpServlet {
   
   private ServletContext application;
   private String imageFolderPath;
   
   @Override
   public void init(ServletConfig config) throws ServletException {
      
      super.init(config);
      application = getServletContext();
      imageFolderPath = application.getInitParameter("imageFolderPath");
   }
   
   public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

      /*
      req.setCharacterEncoding("UTF-8");

      resp.setCharacterEncoding("utf-8");
      resp.setContentType("text/html; charset=utf-8");

      String imageFolder = "D:/contents/images";

      File imageFile = new File(imageFolder);
      String[] images = imageFile.list();

      PrintWriter out = resp.getWriter();
      out.println("<html>");
      out.println("<body>");
      out.println("<h4> 이미지 파일 선택</h4>");
      out.println("<form action=" + "/second/image" + ">");

      out.println("<select name=" + "name" + ">");
      for (String image : images)
         out.println("<option>" + image + "</option>");
      out.println("</select>");
      out.println("<button type=\"submit\">Submit</button>");
      out.println("</form>   ");
      out.println("</html>");
      */
      
      //imageFolderPath = "D:/contents/images";
      File imageFolder = new File(imageFolderPath);
      
      String[] children = imageFolder.list((file,name)->{
         String mime = application.getMimeType(name);
         //System.out.println(mime);
         return mime != null && mime.startsWith("image");
      });
      
      
      String pattern = "<option>%s</option>\n";
      StringBuffer options = new StringBuffer();
      for(String image : children) {
         options.append(String.format(pattern, image));
      }
      
      
      StringBuffer html = new StringBuffer();
      
//      html.append("<html>");
//      html.append("   <body>                                  ");
//      html.append("      <h4> 이미지 파일 선택 </h4>         ");
//      html.append("      <form action='"+ req.getContextPath()+"/image'>       ");
//      html.append("         <select name = 'name'>          ");
//      html.append(options);
//      html.append("         </select>                       ");
//      html.append("<input type='submit' value='전송'>");
//      html.append("      </form>                             ");
//      html.append("   </body>                                 ");
//      html.append("</html>                                    ");
      
      resp.setContentType("text/html; charset=utf-8");
      
      PrintWriter out = null;
      try {
         out = resp.getWriter();
         out.println(html);
      } finally {
         if(out != null) out.close();
      }
   }

}