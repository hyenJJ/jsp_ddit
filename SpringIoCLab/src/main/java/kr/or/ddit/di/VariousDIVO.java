package kr.or.ddit.di;

import java.io.File;
import java.util.Properties;

import org.springframework.core.io.Resource;

import lombok.Data;

@Data
public class VariousDIVO {
	private int number;
	private double db1Number;
	private boolean boolData;
	private char ch;
	private String text;
	
	private Resource fsFile;
	private Resource cpFile;
	
	private Properties dbInfo;
	
}
