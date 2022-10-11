package kr.or.ddit.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

@WebServlet("/file/upload.do")
public class FileUploadServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String tmpDir = "D:\\temp";
		DiskFileItemFactory itemFactory = new DiskFileItemFactory(10*1024, new File(tmpDir));
		ServletFileUpload uploadHandler = new ServletFileUpload(itemFactory);
		// request의 body 영역을 파싱해준다
		
		try {
			List<FileItem> parts = uploadHandler.parseRequest(req);
			
			String encoding = req.getCharacterEncoding();
			
			Map<String, Object> model = new HashMap<>(); //prodVO
			
			for(FileItem part : parts) {
				String partName = part.getFieldName();
				
				if(part.isFormField()) {
					String parameter = part.getString(encoding);
					model.put(partName, parameter);				
				}else { 
					String savePath = uploadFile(part);
					model.put(partName, savePath);
				}
			}
			
			req.getSession().setAttribute("model", model);
			resp.sendRedirect(req.getContextPath() + "/12/uploadForm.jsp");
		} catch (FileUploadException e) {
			
			e.printStackTrace();
		}
		
	
	}

	private String uploadFile(FileItem part) throws IOException {
		
		String saveFolderURL = "/resources/prodImages";
		
		ServletContext application =  getServletContext();
		String saveFolderPath = application.getRealPath(saveFolderURL); // url에 해당하는 진짜  파일 주소 
		
		String filename = part.getName();// 원본 파일 이름
		
		File saveFile = new File(saveFolderPath, filename); // 저장파일을 원본이름으로 저장하기
		
		try(
			InputStream is = part.getInputStream();
		) {
			FileUtils.copyInputStreamToFile(is, saveFile);
			return saveFolderURL + "/" + saveFile.getName();
		}
		
	}

}
