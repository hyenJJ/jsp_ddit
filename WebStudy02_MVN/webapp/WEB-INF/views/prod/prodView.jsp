<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<table class="table table-bordered">
   <tr>
      <th>상품명</th>
      <td>${prod.prodName }</td>
   </tr>
   <tr>
      <th>분류명</th>
      <td>${prod.lprodNm }</td>
   </tr>
   <tr>
   <th>거래처</th>
   <td>
      <table class="table table-bordered">
         <thead>
            <tr>
               <th>거래처명</th>
               <th>소재지</th>
               <th>담당자명</th>
               <th>연락처</th>
               <th>거래은행명</th>
               <th>은행계좌</th>
            </tr>
         </thead>
         <tbody>
            <c:set var="buyer" value="${prod.buyer }" />
            <tr>
               <td>${buyer.buyerName }</td>
               <td>${buyer.buyerAdd1 }</td>
               <td>${buyer.buyerCharger }</td>
               <td>${buyer.buyerComtel }</td>
               <td>${buyer.buyerBankname }</td>
               <td>${buyer.buyerBankno }</td>
            </tr>
         </tbody>
      </table>
   </td>
   <tr>
      <th>구매가</th>
      <td>${prod.prodCost }</td>
   </tr>
   <tr>
      <th>판매가</th>
      <td>${prod.prodPrice }</td>
   </tr>
   <tr>
      <th>세일가</th>
      <td>${prod.prodSale }</td>
   </tr>
   <tr>
      <th>개요</th>
      <td>${prod.prodOutline }</td>
   </tr>
   <tr>
      <th>상세정보</th>
      <td>${prod.prodDetail }</td>
   </tr>
   <tr>
      <th>상품이미지</th>
      <td>${prod.prodImg }</td>
   </tr>
   <tr>
      <th>총재고</th>
      <td>${prod.prodTotalstock }</td>
   </tr>
   <tr>
      <th>입고일</th>
      <td>${prod.prodInsdate }</td>
   </tr>
   <tr>
      <th>적정재고</th>
      <td>${prod.prodProperstock }</td>
   </tr>
   <tr>
      <th>크기</th>
      <td>${prod.prodSize }</td>
   </tr>
   <tr>
      <th>색상</th>
      <td>${prod.prodColor }</td>
   </tr>
   <tr>
      <th>배송방법</th>
      <td>${prod.prodDelivery }</td>
   </tr>
   <tr>
      <th>단위</th>
      <td>${prod.prodUnit }</td>
   </tr>
   <tr>
      <th>입고량</th>
      <td>${prod.prodQtyin }</td>
   </tr>
   <tr>
      <th>판매량</th>
      <td>${prod.prodQtysale }</td>
   </tr>
   <tr>
      <th>마일리지</th>
      <td>${prod.prodMileage }</td>
   </tr>
   <tr>
      <td colspan="2">
         <c:url value="/prod/prodUpdate.do" var="updateURL">
            <c:param name="what" value="${prod.prodId }" />
         </c:url>
         <input type="button" value="수정" onclick="location.href='${updateURL}'"/>
      </td>
   </tr>
   <tr>
      <th>구매자 정보</th>
      <td>
         <table class="table table-bordered">
            <thead class="table-dark">
               <tr>
                  <th>아이디</th>
                  <th>이름</th>
                  <th>지역</th>
                  <th>마일리지</th>
               </tr>
            </thead>
            <tbody>
            <c:set var="memberList" value="${prod.memberList }" />
            <c:if test="${not empty memberList }">
               <c:forEach items="${memberList }" var="member">
                  <tr>
                     <td>${member['memId'] }</td>
                     <td>${member['memName'] }</td>
                     <td>${member['memAdd1'] }</td>
                     <td>${member['memMileage'] }</td>
                  </tr>
               </c:forEach>
            </c:if>
            <c:if test="${empty memberList }">
               <tr>
                  <td colspan="4">구매 기록 없음.</td>
               </tr>
            </c:if>
            </tbody>
         </table>
      </td>
   </tr>
</table>











