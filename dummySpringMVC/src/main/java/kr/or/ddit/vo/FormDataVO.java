package kr.or.ddit.vo;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;

/**
 *  VO           DTO                     
 *  ValueObject, DataTransferObject , JavaBean, Model
 *  
 *  1. 값을 가질 수 있는 property  정의.
 *  2. property 캡슐화
 *  3. 일정한 절차에 따라 값을 변경할 수 있는 interface 메소드 제공.
 *      getter/setter
 *      
 *  4. 상태를 비교할 수 있는 인터페이스 메소드 제공. : hashCode, equals
 *       ==, equals
 *       
 *  5. 상태를 확인할 수 있는 인터페이스 메소드 제공. : toString
 *  
 *  6. 직렬화 가능. : Serializable
 *  
 *  7. property 보호 설정. transient
 *  
 *
 */
public class FormDataVO implements Serializable {
	private String dataId;
	
	private String paramIpt1;
	private Integer paramIpt2;
	private String paramIpt3;
	private transient String paramIpt4;
	private String[] paramIpt5;
	private String paramIpt6;
	private String[] paramIpt7;
	private String paramIpt8;
	
//	private Part filePart;
//	private File filePart;
	private byte[] filePart;  //BLOB
	
	
	
	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getParamIpt1() {
		return paramIpt1;
	}
	
	public void setParamIpt1(String paramIpt1) {
		this.paramIpt1 = paramIpt1;
	}
	
	

	public Integer getParamIpt2() {
		return paramIpt2;
	}

	public void setParamIpt2(Integer paramIpt2) {
		this.paramIpt2 = paramIpt2;
	}

	public String getParamIpt3() {
		return paramIpt3;
	}

	public void setParamIpt3(String paramIpt3) {
		this.paramIpt3 = paramIpt3;
	}

	public String getParamIpt4() {
		return paramIpt4;
	}

	public void setParamIpt4(String paramIpt4) {
		this.paramIpt4 = paramIpt4;
	}

	public String[] getParamIpt5() {
		return paramIpt5;
	}

	public void setParamIpt5(String[] paramIpt5) {
		this.paramIpt5 = paramIpt5;
	}

	public String getParamIpt6() {
		return paramIpt6;
	}

	public void setParamIpt6(String paramIpt6) {
		this.paramIpt6 = paramIpt6;
	}

	public String[] getParamIpt7() {
		return paramIpt7;
	}

	public void setParamIpt7(String[] paramIpt7) {
		this.paramIpt7 = paramIpt7;
	}

	public String getParamIpt8() {
		return paramIpt8;
	}

	public void setParamIpt8(String paramIpt8) {
		this.paramIpt8 = paramIpt8;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataId == null) ? 0 : dataId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormDataVO other = (FormDataVO) obj;
		if (dataId == null) {
			if (other.dataId != null)
				return false;
		} else if (!dataId.equals(other.dataId))
			return false;
		return true;
	}

	
	
	
	@Override
	public String toString() {
		return "FormDataVO [dataId=" + dataId + ", paramIpt1=" + paramIpt1 + ", paramIpt2=" + paramIpt2 + ", paramIpt3="
				+ paramIpt3 + ", paramIpt5=" + Arrays.toString(paramIpt5) + ", paramIpt6=" + paramIpt6 + ", paramIpt7="
				+ Arrays.toString(paramIpt7) + ", paramIpt8=" + paramIpt8 + "]";
	}
	
	
	

}
