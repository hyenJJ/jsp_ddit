package kr.or.ddit.board.view;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.board.vo.AttatchVO;

public class DownloadView extends AbstractView {
	
	@Value("#{appInfo.attatchFolder}")
	private File saveFolder;

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse resp) throws Exception {
		
		AttatchVO attatch = (AttatchVO) model.get("attatch");
		
		String saveName = attatch.getAttSavename();
		
		File file = new File(saveFolder, saveName);
		
		String fileName = attatch.getAttFilename();
//		%2T - percent encoding, URL encoding 
		fileName = URLEncoder.encode(fileName, "UTF-8"); // 한글 설정 
 		fileName = fileName.replaceAll("\\+", " "); // +를 일반문자로 바꾸고 공백으로 바꿔라
 		
		//mime 셋팅
		resp.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);// 원래 파일이였던 mime data를 가져와야함
		                // "application/octet-stream" : 8bit(1bite)기반의 stream -> 1bite단위로 나간다 
		resp.setContentLengthLong(attatch.getAttFilesize());
	
		//원본 filename resp에 담아주기 								 공백을 fileName으로 인식하게 "" 안에 넣어줌
		resp.setHeader("Content-Disposition","attatchment;filename=\"" + fileName +"\"");  
								//inline - 바로 실행하겠다 , attatchment - 바로 실행안하고 다운로드 
		
		try(
			OutputStream os = resp.getOutputStream();
				
		){
			FileUtils.copyFile(file, os);
			
		}

	}

}
