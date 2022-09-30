package kr.or.ddit.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 
 * 단순 키워드 검색에 활용.
 *
 */
@AllArgsConstructor
@Getter
@ToString
public class SearchVO {

	private String searchType;
	private String searchWord;
}
