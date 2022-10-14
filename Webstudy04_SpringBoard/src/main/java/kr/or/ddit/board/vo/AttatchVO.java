package kr.or.ddit.board.vo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="attNo")
@ToString(exclude="adaptee")
@NoArgsConstructor
public class AttatchVO {

	
	private MultipartFile adaptee; //partdata 안에 이진데이터가 있기때문에 tostring할수가 ㅇ벗음
	
	// 기본 생성자가 없어지면서 adaptee없이는 attatch를 사용할수 없음
	public AttatchVO(MultipartFile adaptee) {
		super();
		this.adaptee = adaptee;
		this.attFilename = adaptee.getOriginalFilename();
		this.attSavename = UUID.randomUUID().toString();
		this.attMime = adaptee.getContentType();
		this.attFilesize = adaptee.getSize();
		this.attFancysize = FileUtils.byteCountToDisplaySize(attFilesize) ;
	}
	
	@NotNull
	private Integer attNo;
	private Integer boNo;
	@NotBlank
	private String attFilename;
	@NotBlank
	private String attSavename;
	private String attMime;
	@NotNull
	private Long attFilesize;
	@NotBlank
	private String attFancysize;
	private Integer attDownload;
	
	public void saveTo(File saveFolder) throws IllegalStateException, IOException {
		if(adaptee==null) return; 
		
		File saveFile = new File(saveFolder,attSavename);
		adaptee.transferTo(saveFile);
		
	}
}
