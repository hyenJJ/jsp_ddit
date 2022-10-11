package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="buyerId")
public class BuyerVO implements Serializable{
	
	
	
	private String buyerBankname;
	private String buyerZip;
	private String buyerAdd1;
	private String buyerAdd2;
	private String buyerComtel;
	private String buyerFax;
	private String buyerMail;
	private String buyerCharger;
	private String buyerTelext;
	private String buyerId;
	private String buyerName;
	private String buyerLgu;
	private String buyerBank;
	private String buyerBankno;
	
	
	// 해당 거래처와의 거래 품목수. count
	private int prodCount;
	
	// 해당 거래처와의 거래 품목 리스트. has many 
	private List<ProdVO> prodList;

}


