package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.Part;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.file.MultipartFile;
import kr.or.ddit.validate.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = "prodDetail") //제외하고 싶은 property 명을 추가 
@EqualsAndHashCode(of = {"prodId"}) //equals 객체를 설정해 주지 않으면 모든게 대상이 됨  
//@Setter
//@Getter
@Data   //=> data 만해도 모든 설정이 가능 (기본설정)
@AllArgsConstructor
@NoArgsConstructor  //prodVO
public class ProdVO implements Serializable{
	
	private int rnum;
	
	
	@NotBlank(groups = {UpdateGroup.class})
	private String prodId;
	
	@NotBlank
	private String prodName;
	
	@NotBlank
	private String prodLgu;
	
	private String lprodNm; //상품 분류명은 가져올 데이터가 별로 없음
	
	@NotBlank
	private String prodBuyer;
	
	@NotNull
	@Min(0)
	private Integer prodCost;
	
	@NotNull
	@Min(0)
	private Integer prodPrice;
	
	@NotNull
	private Integer prodSale;
	
	@NotBlank
	private String prodOutline;
	
	@JsonIgnore
	private transient String prodDetail; //직렬화 제외
	
	@NotBlank
	private String prodImg; //데이터 베이스와 소통하기 위한 prodvo
	
	private MultipartFile prodImage; 
	
	public void setProdImage(MultipartFile prodImage) {
		
		if(prodImage != null && !prodImage.isEmpty()) {
			this.prodImage = prodImage;
			this.prodImg = UUID.randomUUID().toString();
		}
	}
	
	public void saveTo(File saveFolder) throws IOException {
		if(prodImage==null) return;
		File saveFile = new File(saveFolder, this.prodImg);
		prodImage.transferTo(saveFile);
	}
	
	@NotNull
	@Min(0)
	private Integer prodTotalstock;
	
	private String prodInsdate;
	
	@NotNull
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;
	
	private BuyerVO buyer; //has A 
	
	private Set<MemberVO> memberList; // has Many
	
	//mem_count를 담을 수 있는 property 생성
	private Integer memCount;
	
}
