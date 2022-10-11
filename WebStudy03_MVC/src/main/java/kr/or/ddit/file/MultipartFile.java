package kr.or.ddit.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


/**
 * 
 * 업로드되는 file part 하나를 캡슐화 하기 위한 객체.
 *
 */
public interface MultipartFile {
	
	public byte[] getBytes() throws IOException;
	public String getContentType();
	public InputStream getInputStream() throws IOException;
	public String getName();
	public String getOriginalFilename();
	public long getSize();
	public boolean isEmpty();
	public void transferTo(File dest) throws IOException;

}
