<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<c:set var="boardList" value="${pagingVO.dataList }" />
<table class="table table-bordered table-strip">
   <thead class="table-dark">
      <tr>
         <th>글번호</th>
         <th>제목</th>
         <th>작성자</th>
         <th>작성일</th>
         <th>조회수</th>
         <th>추천수</th>
      </tr>
   </thead>
   <tbody id="listBody">
      <c:choose>
         <c:when test="${not empty boardList }">
            <c:forEach items="${boardList }" var="board">
               <c:url value="/board/boardView.do" var="viewURL">
                  <c:param name="what" value="${board.boNo }" />
               </c:url>
               <tr>
                  <td>${board.boNo }</td>
                  <td><a href="${viewURL }">${board.boTitle }</a></td>
                  <td>${board.boWriter }</td>
                  <td>${board.boDate }</td>
                  <td>${board.boHit }</td>
                  <td>${board.boRec }</td>
               </tr>
            </c:forEach>
         </c:when>
         <c:otherwise>
            <tr>
               <td colspan="6"> 조건에 맞는 글이 없음. </td>
            </tr>
         </c:otherwise>
      </c:choose> 
   </tbody>
</table>
<form id="searchForm">
   <input type="hidden" name="page" />
   <input type="hidden" name="searchType" value="${simpleCondition.searchType}"/>
   <input type="hidden" name="searchWord" value="${simpleCondition.searchWord}"/>
</form>
<div class="pagingArea">
    ${pagingVO.pagingHTML } 
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
         class="form-control" value="${pagingVO.simpleCondition.searchWord}"
      />
   </div>
   <div class="col-auto">
      <input type="button" value="검색" id="searchBtn"
         class="btn btn-primary"
      />
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
   
   
   let searchForm = $("#searchForm");
   
</script>















