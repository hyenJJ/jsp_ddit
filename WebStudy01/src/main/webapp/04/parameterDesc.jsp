<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h4>요청 파라미터 처리</h4>
   <form action="<%=request.getContextPath() %>/formDataProcess" method="post" 
               enctype="multipart/form-data">
      <pre>
         <input type="text" name="paramIpt1" placeholder="paramIpt1"/>
         <input type="number" name="paramIpt2" placeholder="paramIpt2"/>
         <input type="date" name="paramIpt3" placeholder="paramIpt3"/>
         <input type="radio" name="paramIpt4" id="paramIpt4_R1" placeholder="paramIpt4" value="R1"/>
         <label for="paramIpt4_R1">
         R1
         </label>
         <input type="radio" name="paramIpt4" id="paramIpt4_R2" placeholder="paramIpt4" value="R2"/>
         <label for="paramIpt4_R2">
         R2
         </label>
         <label><input type="checkbox" name="paramIpt5" value="CHECK1">CHECK1</label>
         <label><input type="checkbox" name="paramIpt5" value="CHECK2">CHECK2</label>
         <label><input type="checkbox" name="paramIpt5" value="CHECK3">CHECK3</label>
         <select name="paramIpt6" required>
            <option value>선택</option>
            <option value="옵션1">OPT1</option>
            <option value="옵션2">OPT2</option>
            <option value="옵션3">OPT3</option>
         </select>
         <select name="paramIpt7" multiple>
            <option value="옵션1">OPT1</option>
            <option value="옵션2">OPT2</option>
            <option value="옵션3">OPT3</option>
         </select>
         <textarea rows="" cols="" name="paramIpt8" placeholder="paramIpt8"></textarea>
         <input type = "file" name="filePart" />
         <input type="submit" value="전송">
      </pre>
   </form>
</body>
</html>