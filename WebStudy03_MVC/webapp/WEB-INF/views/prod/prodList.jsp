<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!-- // 상품아이디, 상품명, 판매가, 구매가, 마일리지.  -->
<!-- // + 분류명, 거래처명, 해당 상품의 구매자수(mem_count) -->
<table class="table table-bordered table-striped">
   <thead class="table-dark">
      <tr>
         <th>상품명</th>
         <th>분류명</th>
         <th>판매가</th>
         <th>구매가</th>
         <th>마일리지</th>
         <th>거래처명</th>
         <th>구매자수</th>
      </tr>
   </thead>
   <tbody id="listBody">
<%--       <c:set var="prodList" value="${pagingVO.dataList }" /> --%>
<%--       <c:choose> --%>
<%--          <c:when test="${not empty prodList }"> --%>
<%--             <c:forEach items="${prodList }" var="prod"> --%>
<%--                <c:url value="/prod/prodView.do" var="viewURL"> --%>
<%--                   <c:param name="what" value="${prod.prodId }" /> --%>
<%--                </c:url> --%>
<!--                <tr> -->
<%--                   <td>${prod.rnum }</td> --%>
<%--                   <td><a href="${viewURL }">${prod.prodName }</a></td> --%>
<%--                   <td>${prod.lprodNm }</td> --%>
<%--                   <td>${prod.prodPrice }</td> --%>
<%--                   <td>${prod.prodCost }</td> --%>
<%--                   <td>${prod.prodMileage }</td> --%>
<%--                   <td>${prod.buyer.buyerName }</td> --%>
<%--                   <td>${prod.memCount }</td> --%>
<!--                </tr> -->
<%--             </c:forEach> --%>
<%--          </c:when> --%>
<%--          <c:otherwise> --%>
<!--             <tr> -->
<!--                <td colspan="8">상품 없음.</td> -->
<!--             </tr> -->
<%--          </c:otherwise> --%>
<%--       </c:choose> --%>
   </tbody>
   <tfoot>
      <tr>
         <td colspan="8">
            <div class="pagingArea mb-3">
<%--                ${pagingVO.pagingHTML } --%>
            </div>
            <div id="searchUI" class="border border-primary border-1 row g-3">
               <h4>검색조건 입력 UI - searchUI</h4>
               <div class="col-auto">
                  <select name="prodLgu" class="form-select">
                     <option value>상품분류</option>
                  </select>
               </div>
               <div class="col-auto">
                  <select name="prodBuyer" class="form-select">
                     <option value>거래처</option>
                     <option value="P10101">삼성전자</option>
                  </select>
               </div>
               <div class="col-auto">
                  <input type="text" name="prodName" value="${pagingVO.detailCondition.prodName}"
                      class="form-control" placeholder="상품명"
                  />
               </div>
               <div class="col-auto">
                  <input type="button" class="btn btn-primary" value="검색" id="searchBtn"/>
               </div>
            </div>
         </td>
      </tr>
   </tfoot>
</table>
<form id="searchForm" class="border border-danger border-3">
   <h4>검색조건 전송 UI - searchForm</h4>
   <input type="text" name="page" />
   <input type="text" name="prodLgu" value="${pagingVO.detailCondition.prodLgu}"/>
   <input type="text" name="prodBuyer"  value="${pagingVO.detailCondition.prodBuyer}"/>
   <input type="text" name="prodName"  value="${pagingVO.detailCondition.prodName}"/>
</form>
<script>
   $.ajax({
      url : "${pageContext.request.contextPath}/prod/getLprodList.do",
      dataType : "json",
      success : function(resp) {
         let lprodList = resp.model;
         let options = [];
         $.each(lprodList, function(index, lprod){
            let option = $("<option>").attr("value", lprod.lprodGu)
                                 .text(lprod.lprodNm);
            if("${pagingVO.detailCondition.prodLgu}" == lprod.lprodGu){
               option.prop("selected", true);
            }
            
            options.push(option);
         });
         let prodLguTag = searchUI.find("[name=prodLgu]");
         prodLguTag.append(options);
         prodLguTag.trigger("change");
      },
      error : function(errorResp) {
         console.log(errorResp.status);
      }
   });

   let searchUI = $("#searchUI").on("click", "#searchBtn", function(event){
      let inputTags = searchUI.find(":input[name]");
      $.each(inputTags, function(index, inputTag){
         let name = $(this).attr("name");
         let value = $(this).val();
         searchForm.get(0)[name].value = value;
      });
      searchForm.submit();
   }).on("change", "[name=prodLgu]", function(event){
      let lprodGu = $(this).val();
      $.ajax({
         url : "${pageContext.request.contextPath}/prod/getBuyerList.do",
         data : {
            lprodGu:lprodGu
         },
         dataType : "json",
         success : function(resp) {
            let buyerList = resp.model;
            let options = [];
            $.each(buyerList, function(index, buyer){
               let option = $("<option>").attr("value", buyer.buyerId)
                                 .addClass(buyer.buyerLgu)
                                 .text(buyer.buyerName);
               if("${pagingVO.detailCondition.prodBuyer}" == buyer.buyerId){
                  option.prop("selected", true);
               }
               options.push(option);
            });
            let prodBuyerTag = searchUI.find("[name=prodBuyer]");
            prodBuyerTag.find("option:not(:first)").remove();
            prodBuyerTag.append(options);
         },
         error : function(errorResp) {
            console.log(errorResp.status);
         }
      });
   });
   
   let makeTrTag = function(prod){
      let viewURL = "${pageContext.request.contextPath}/prod/prodView.do?what="+prod.prodId;
      let trTag = $("<tr>").append(
         $("<td>").html(prod.rnum)
         , $("<td>").html(
            $("<a>").attr("href", viewURL)
                  .text(prod.prodName)
         )
         , $("<td>").html(prod.lprodNm)
         , $("<td>").html(prod.prodPrice)
         , $("<td>").html(prod.prodCost)
         , $("<td>").html(prod.prodMileage)
         , $("<td>").html(prod.buyer.buyerName)
         , $("<td>").html(prod.memCount)
      );
      return trTag;
   }
   let listBody = $("#listBody");
   
   let pageTag = $("[name=page]");
   
   let pagingArea = $(".pagingArea").on("click", "a", function(event){
      event.preventDefault();
      let page = $(this).data("page");
      if(!page) return false;
      pageTag.val(page);
      searchForm.submit();
      return false;
   });
   
   
   let searchForm = $("#searchForm").on("submit", function(event){
      event.preventDefault();
      pagingArea.empty();
      listBody.empty();
      let url = this.action;
      let method = this.method;
      let data = $(this).serialize();
      $.ajax({
         url : url,
         method : method,
         data : data,
         dataType : "json", // Accept vs Content-Type
         success : function(resp) {
            let pagingVO = resp.pagingVO;
            let prodList = pagingVO.dataList;
            
            let trTags = [];
            if(prodList.length > 0){
               $.each(prodList, function(index, prod){
                  let trTag = makeTrTag(prod);
                  trTags.push(trTag);
               });
            }else{
               let trTag = $("<tr>").html(
                           $("<td>").attr("colspan", "8")
                                 .text("상품 없음.")
                        );
               trTags.push(trTag);
            }
            console.log(trTags);
            pagingArea.html(pagingVO.pagingHTML);
            listBody.append(trTags);
            
            pageTag.val("");
         },
         error : function(errorResp) {
            console.log(errorResp.status);
         }
      });
      return false;
   }).trigger("submit");
   
</script>









