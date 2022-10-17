package kr.or.ddit.board.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
//값을 임의로 넣어주면 안되는 것들도 있기때문에 get만

@Getter
@NoArgsConstructor
public class PagingVO<T> {
	private int totalRecord;
	private int currentPage;
	private int screenSize = 10;
	private int blockSize = 5;
	private int totalPage;
	
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	
	//검색조건
	private SearchVO simpleCondition; 
	
	private T detailCondition; // 상세검색 -> 검색의 대상이 되는 
	
	private List<T> dataList;
	
	public void setDetailCondition(T detailCondition) {
		this.detailCondition = detailCondition;
	}
	
	public void setSimpleCondition(SearchVO simpleCondition) {
		this.simpleCondition = simpleCondition;
	}
	
	public PagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = (totalRecord + (screenSize-1)) / screenSize;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		
		endRow = currentPage * screenSize;
		startRow = endRow - (screenSize-1);
		endPage = blockSize * ((currentPage + (blockSize-1)) / blockSize);
		startPage = endPage - (blockSize-1);
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	                              //?page=%d
	  private static final String PATTERN = "<a href='#' data-page='%d'>%s</a>";
	   public String getPagingHTML_Simple() {
	      StringBuffer html = new StringBuffer();
	      
	      endPage = endPage > totalPage ?  totalPage : endPage;
	      
	      if(startPage > blockSize) {
	         html.append(
	            String.format(PATTERN, startPage-blockSize, "이전")   
	         );
	      }
	      
	      for(int page = startPage; page <= endPage; page++) {
	         if(page == currentPage) {
	            html.append(page);
	         }else {
	            html.append(
	               String.format(PATTERN, page, page)
	            );
	         }
	      }
	      
	      if(endPage < totalPage) {
	         html.append(
	            String.format(PATTERN, endPage+1, "다음")   
	         );
	      }
	      
	      return html.toString();
	   }
	   private static final String BSPATTERN = "<li class='page-item %s'>" +
		         "<a class='page-link' href='#' data-page='%d'>%s</a>" +
		         "</li>";
		   
		   public String getPagingHTML() {
		      StringBuffer html = new StringBuffer();
		      html.append(" <nav aria-label='Page navigation example'>        ");
		      html.append("   <ul class='pagination justify-content-center'>  ");
		      // previous
		      if(startPage > blockSize) {
		         html.append(
		            String.format(
		               BSPATTERN 
		               , ""
		               , 1
		               , "<<"      
		            )   
		         );
		      }
		      
		      boolean disabled = startPage <= 1;
		      html.append(
		         String.format(
		            BSPATTERN 
		            , disabled?"disabled":""
		            , disabled?1:startPage-blockSize
		            , "이전"      
		         )   
		      );
		      // page link
		      endPage = endPage > totalPage ?  totalPage : endPage;
		      for(int page = startPage; page <= endPage; page++) {
		         boolean active = page == currentPage;
		         html.append(
		            String.format(
		               BSPATTERN 
		               , active?"active":""
		               , page
		               , page      
		            )   
		         );
		      }
		      // next
		      disabled = endPage >= totalPage;
		      html.append(
		         String.format(
		            BSPATTERN 
		            , disabled?"disabled":""
		            , disabled?totalPage:endPage+1
		            , "다음"      
		         )   
		      );
		      // to end
		      if(totalPage > blockSize && totalPage > endPage ) {
		         html.append(
		            String.format(
		               BSPATTERN 
		               , ""
		               , totalPage
		               , ">>"      
		            )   
		         );
		      }

		      html.append("   </ul>                                           ");
		      html.append(" </nav>                                            ");
		      return html.toString();
		   }
}
