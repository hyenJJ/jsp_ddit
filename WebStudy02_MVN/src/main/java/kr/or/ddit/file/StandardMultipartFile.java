package kr.or.ddit.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class StandardMultipartFile implements MultipartFile {

	
	private Part filePart;
	
	
	
	public StandardMultipartFile(Part filePart) {
		super();
		this.filePart = filePart;
	}

	@Override
	public byte[] getBytes() throws IOException {
		
		return IOUtils.toByteArray(getInputStream());
	}

	@Override
	public String getContentType() {
		
		return filePart.getContentType() ;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return filePart.getInputStream();
	}

	@Override
	public String getName() {
		
		return filePart.getName();
	}

	@Override
	public String getOriginalFilename() {
		
		return filePart.getSubmittedFileName();
	}

	@Override
	public long getSize() {
	
		return filePart.getSize();
	}

	@Override
	public boolean isEmpty() {
		
		return getSize()==0 || StringUtils.isBlank(getOriginalFilename());
		//사이즈가 0이거나 원본파일명이 존재하지 않거나
	}

	@Override
	public void transferTo(File dest) throws IOException {
		filePart.write(dest.getCanonicalPath());

	}

}
