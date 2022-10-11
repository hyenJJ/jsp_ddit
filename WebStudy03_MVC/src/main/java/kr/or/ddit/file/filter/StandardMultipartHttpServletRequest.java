package kr.or.ddit.file.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

import kr.or.ddit.file.MultipartFile;
import kr.or.ddit.file.StandardMultipartFile;

//어댑터는 기본 생성자를 가질 수 없다
/**
 * 원본 request 가 가진 Part -> MultipartFile 객체로 wrapping.
 * 
 *
 */
public class StandardMultipartHttpServletRequest extends HttpServletRequestWrapper{
															// request -> adaptee
	
	private Map<String, List<MultipartFile>> files;
	
	public StandardMultipartHttpServletRequest(HttpServletRequest request) throws IOException, ServletException {
		super(request);    															//던져진 이 예외는 필터로 감 
		parseRequest(request);
	
	}
	
	private void parseRequest(HttpServletRequest req) throws IOException, ServletException {
		Collection<Part> parts  =  req.getParts();
		files = new LinkedHashMap<>();
		
		Iterator<Part> it =  parts.iterator();
		while (it.hasNext()) {
			Part part = (Part) it.next();
			String contentType = part.getContentType();
			if(contentType == null) continue;
			MultipartFile file = new StandardMultipartFile(part); // PART하나를 ADAPTER로
			//adaptee가 갖고있지 않은 interface를 사용하기 위해서
			
			String partName = file.getName();
			List<MultipartFile> already = files.get(partName);
			if(already == null) {
				already = new ArrayList<>();
				files.put(partName, already);
			}
			
			already.add(file);
		}
	}
	
	public MultipartFile getFile(String partName){
		List<MultipartFile> fileList =  files.get(partName);
		if(fileList!=null && !fileList.isEmpty()) {
			return fileList.get(0);
			
		}else {
			return null;
		}
		
	}
	public List<MultipartFile> getFiles( String partNmae ){
		return files.get( partNmae );
	}
}

