package kr.or.ddit.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Stream 이용한 IO
 * 
 * Stream : 연속성을 지닌 일련의 데이터 집합 이면서 동시에 데이터를 읽거나 쓰기 위한
 *          단방향(***) 통로
 *     
 *     
 *  #스트림을 사용한 IO 단계#
 *  1. media(매체)를 어플리케이션에서 제어할 수 있는 객체의 형태로 생성.
 *       ex) new File(filesystem Path), Socket, memory
 *       
 *  2. 1차 스트림을 매체에 연결.
 *       ex) new FileInputStream(file)
 *       
 *  3. 2차 스트림을 1차 스트림에 연결.(optional / 1차가 있어야만 함)
 *       ex) new BufferedInputStream(inputStream)
 *      
 *  4. EOF(EOS, -1, null) 까지 반복적인 read 
 *   
 *  5. close(닫지 않으면 재활용 불가)
 *  
 *          
 *          
 *  stream 종류
 *  1. 전송 데이터 크기 
 *     1) byte stream  (~~Stream)
 *          FileInputStream  / FileOutputStream
 *          SocketInputStream / SocketOuputStream
 *          ByteArrayInputStream / ByteArrayOutputStream
 *           
 *     2) character stream (~~Reader/Writer)
 *           FileReader(입력) / FileWriter(출력)
 *           
 *  2. stream 생성 방법에 따라.
 *     1) 1차 stream
 *     2) 2차 stream(연결형 스트림)     
 *         BufferedReader (readLine)
 *         FilteredStream : DataInputStream
 *         Objectinput[Output]Stream : Serializeble 객체 직렬화/역직렬화 스트림. (모든객체가 직렬화 가능X)
 *                                     
 *            
 *
 */
public class StreamDesc {
	
	public static void main(String[] args) throws Exception {
		
		//readAndPrintFileSystemResource();
		//readAndPrintClassPathResource();
		readAndWriteToFileSystemResource();
	}
	
	
	private static void readAndWriteToFileSystemResource() throws URISyntaxException, IOException{
		String logo = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png"; //논리 주소
		String filePath="D:\\contents\\googleLogo.png"; // 저장할 경로
		
		
		
		URI logoURI = new URI(logo);
		URL logoURL = logoURI.toURL();
		
		File file = new File(filePath);
		
	try (
	    InputStream is = logoURL.openStream();
	    FileOutputStream fos = new FileOutputStream(file);
	    
	    BufferedInputStream bis = new BufferedInputStream(is);
	    BufferedOutputStream bos = new BufferedOutputStream(fos);
	){
		
		int temp = -1;
		
		while((temp=bis.read())!=-1) {
			bos.write(temp);
		}
		
	}
	
	}
	
	
	private static void readAndPrintClassPathResource() throws IOException{
//		String resCpPath = "/kr/or/ddit/medias/오래된 노래_utf8.txt"; // 논리 주소
		String resCpPath = "/kr/or/ddit/medias/오래된 노래.txt"; // 논리 주소
		
		// /LanguageLab/src/main/resources/kr.or.ddit.medias/오래된 노래.txt

try(	
			
		InputStream is = StreamDesc.class.getResourceAsStream(resCpPath); //1차스트림
	
				
		InputStreamReader isr = new InputStreamReader(is,"MS949"); // 1차와 2차의 젠더 역할
		
		BufferedReader br = new BufferedReader(isr);  //2차 스트림
		
	){		
       String temp = null; 
       
       // step4
       while( (temp = br.readLine()) != null) {
    	   System.out.println(temp);
	 
       }
	}
	
	
}	
	
	private static void readAndPrintFileSystemResource() throws IOException {
		String resPath = "D:\\contents\\another day.txt"; //물리 주소
		

		
		
//		try with resource 문법(1.7버전 이후)
//		try(
//		   // Closable 객체 생성. -> 자동으로 finally 가 추가되고, close됨.
//				
//	    ){
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		// step1
		File file = new File(resPath);
		
		try(
								
				// step2
				FileReader reader = new FileReader(file);
				
				// step3
				BufferedReader br = new BufferedReader(reader);
				
		){
				String temp = null; 
				
				// step4
				while( (temp = br.readLine()) != null) {
					System.out.println(temp);
				}
			
			
		} 
		
		
		// step5
//		br.close();
//		reader.close();
		
	}
}
