package kr.or.ddit.di;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceLoaderTestView {

	public static void main(String[] args) {
		// 1. file system resource : d:/contents/images/cat1.jpg
		// 2. class path resource : /log4j2.xml
		// 3. web resource : https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png

		ResourceLoader loader = new ClassPathXmlApplicationContext();
		
		String fileSystemPath = "file://d:/contents/images/cat1.jpg";
		Resource fsResource =  loader.getResource(fileSystemPath);
		logResource(fsResource);
		
		String qualifiedName = "classpath:/log4j2.xml";
		Resource cpResource =  loader.getResource(qualifiedName);
		logResource(cpResource);
		
		
		String webURL = " https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";
		Resource webResource = loader.getResource(webURL);
		logResource(webResource);		
	}
	
	private static void logResource(Resource res) {
		log.info("로딩된 자원 : {}" , res);
		try {
			log.info("로딩된 파일 : {}", res.getFile());
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			
		}
	}
}
