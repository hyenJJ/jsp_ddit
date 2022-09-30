<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<table class="table table-bordered">
   <thead>
      <tr>
      	 <th>사원번호</th>
         <th>사원이름</th>
         <th>직책</th>
         <th>직속상관 사원번호</th>
         <th>입사일</th>
         <th>급여 외 추가수당</th>
         <th>부서번호</th>
      </tr>
   </thead>
   <tbody>
   	  <c:set var="empList" value="${empList}"></c:set>  
      <c:choose>
         <c:when test="${not empty empList }">
            <c:forEach items="${empList }" var="emp">
               <tr 
                     data-bs-toggle="modal" data-bs-target="#exampleModal">
                  <td>${emp['empno'] }</td>
                  <td>${emp['ename'] }</td>
                  <td>${emp['job'] }</td>
                  <td>${emp['mgr'] }</td>
                  <td>${emp['sal'] }</td>
                  <td>${emp['comm'] }</td>
                  <td>${emp['deptno'] }</td>
               </tr>
            </c:forEach>
         </c:when>
         <c:otherwise>
            <tr>
               <td colspan="6">회원이 없음.</td>
            </tr>
         </c:otherwise>
      </c:choose>
   </tbody>
<%--    <tfoot>
   		<tr>
   			<td colspan="7">
   			<div class="pagingArea">
   			 ${pagingVO.pagingHTML }
   		 	</div>
   		 		<div id="searchUI">
	   		 		<input type="text" name="page"/>
		   			<select name="searchType">
		   				<option value>전체</option>
		   				<option value="name">이름</option>
		   				<option value="address">지역</option>
		   				
		   			</select>
		   			<input type="text" name="searchWord" />	
		   			<input type="button" value="검색" id="searchBtn" />	
	   		 	</div>
   			</td>
   		</tr>
   </tfoot> --%>
</table>