package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.AttatchVO;

@Controller
public class AttatchDownloadController {

	@Inject
	private BoardService service;
	//구현체와 컨트롤러 사이에 결합력을 낮추기 위해,
	// proxy target 생성 하기 위해 서 interface 사용
	
/*	@Value("#{appInfo.attatchFolder}")
	private File saveFolder;   */
	
	@RequestMapping("/board/download.do")
	   public String download(
			      @RequestParam(name="what", required=true) int attNo
			      , Model model
			   )throws IOException {
			      AttatchVO attatch = service.retrieveAttatch(attNo);
			      model.addAttribute("attatch", attatch);
			      return "downloadView";

		
/*		
		* 컨트롤러가 view layer가 해야되는 Response를 대신 하고 있음  -> model 1 
		
		String saveName = attatch.getAttSavename();
		
		File file = new File(saveFolder, saveName);
		
		String fileName = attatch.getAttFilename();
//		%2T - percent encoding, URL encoding 
		fileName = URLEncoder.encode(fileName, "UTF-8"); // 한글 설정 
 		fileName = fileName.replaceAll("\\+", ""); // +를 일반문자로 바꾸고 공백으로 바꿔라
 		
		//mime 셋팅
		resp.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);// 원래 파일이였던 mime data를 가져와야함
		                // "application/octet-stream" : 8bit(1bite)기반의 stream -> 1bite단위로 나간다 
		resp.setContentLengthLong(attatch.getAttFilesize());
	
		//원본 filename resp에 담아주기 								 공백을 fileName으로 인식하게 "" 안에 넣어줌
		resp.setHeader("Content-Disposition","attatchment;filename=\"" + fileName +"\"");  
								//inline - 바로 실행하겠다 , attatchment - 바로 실행안하고 다운로드 
		
		try(
			OutputStream os = resp.getOutputStream()
				
		){
			FileUtils.copyFile(file, os);
			
		}
		*/
		
		
		
		
		
	}
}
