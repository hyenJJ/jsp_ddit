<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%><!-- spring 태그를 통해서 messageBundle안에 있는 p를 통해 가져옴   -->
<c:set var="boardList" value="${pagingVO.dataList }" />
<table class="table table-bordered table-strip">
   <thead class="table-dark">
      <tr>
         <th><spring:message code="board.boNo"/></th>
         <th><spring:message code="board.boTitle"/></th>
         <th><spring:message code="board.boWriter"/></th>
         <th><spring:message code="board.boDate"/></th>
         <th><spring:message code="board.boHit"/></th>
         <th><spring:message code="board.boRec"/></th>
      </tr>
   </thead>
   <tbody id="listBody">
   </tbody>
</table>
<form id="searchForm">
   <input type="hidden" name="page" />
   <input type="hidden" name="searchType" />
   <input type="hidden" name="searchWord" />
</form>
<div class="pagingArea">
   <%-- ${pagingVO.pagingHTML } --%>
</div>
<!-- (제목-title, 작성자-writer, 내용-content, 전체) -->
<div id="searchUI" class="row g-3 justify-content-center">
   <div class="col-auto">
      <select name="searchType" class="form-select">
         <option value>전체</option>
         <option value="title" 
            ${pagingVO.simpleCondition.searchType eq 'title'?'selected':''}
         >제목</option>
         <option value="writer"
            ${pagingVO.simpleCondition.searchType eq 'writer'?'selected':''}
         >작성자</option>
         <option value="content"
            ${pagingVO.simpleCondition.searchType eq 'content'?'selected':''}
         >내용</option>
      </select>
   </div>
   <div class="col-auto">
      <input type="text" name="searchWord" placeholder="검색키워드"
         class="form-control" 
      />
   </div>
   <div class="col-auto">
      <input type="button" value="검색" id="searchBtn"
         class="btn btn-primary"
      />
		<a class="btn btn-success" href="<c:url value='/board/boardInsert.do'/>">새글쓰기</a>
   </div>
</div>
<script type="text/javascript">
   let searchUI = $("#searchUI").on("click", "#searchBtn", function(event){
      let inputTags = searchUI.find(":input[name]");
      $.each(inputTags, function(index, inputTag){
         let name = $(this).attr("name");
         let value = $(this).val();
         searchForm.get(0)[name].value = value;
      });
      searchForm.submit();
   });

   
   let pageTag = $("[name=page]"); 
   let listBody = $("#listBody");
   let pagingArea = $(".pagingArea").on("click", "a", function(event){
      event.preventDefault();
      let page = $(this).data("page");	
      if(!page) return false;
      pageTag.val(page);
      searchForm.submit();
      return false;
   });
   
   
   let makeTrTag = function(board){ //게시글 한건 
      let viewURL = "${pageContext.request.contextPath}/board/boardView.do?what="+board.boNo;
      let trTag = $("<tr>").append(
         $("<td>").html(board.boNo)
         , $("<td>").html(
            $("<a>").attr("href", viewURL)
                  .text(board.boTitle)   
         )
         , $("<td>").html(board.boWriter)
         , $("<td>").html(board.boDate)
         , $("<td>").html(board.boHit)
         , $("<td>").html(board.boRec)

      );
      return trTag;
   }
   
   
  
   
   
   
   let searchForm = $("#searchForm").on("submit", function(event){
      event.preventDefault();
      pagingArea.empty();
      listBody.empty();
      let url = this.action;
      let method = this.method;
      let data = $(this).serialize(); // 제이쿼리화 
      $.ajax({
         url : url,
         method : method,
         data : data,
         dataType : "json", // Accept vs Content-Type
         success : function(pagingVO) {
           	 
            let boardList = pagingVO.dataList;
            
            let trTags = [];
            
            if(boardList.length > 0){ // 데이타가 있다 
               $.each(boardList, function(index, board){ // 반복문 
                  let trTag = makeTrTag(board);
                  trTags.push(trTag);
               });
            }else{ // 데이터가 없다 
               let trTag = $("<tr>").html(
                           $("<td>").attr("colspan", "6")
                                 .text("조건에 맞는 글이 없음.") // = .html()
                        );
               trTags.push(trTag);
            }
            console.log(trTags);
            pagingArea.html(pagingVO.pagingHTML);
            listBody.append(trTags);
            
            pageTag.val(""); //page를 초기화 해줘야 1페이지가 나옴
         },
         error : function(errorResp) {
            console.log(errorResp.status);
         }
      });
      return false;
   }).trigger("submit"); // = .submit()  : 한번 누를 대 마다 검색 하게 
   
   
</script>















