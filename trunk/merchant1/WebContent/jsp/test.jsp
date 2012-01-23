<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 


<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

function onSAVE() {

	if(document.forms[0].typeName.value==''){
		alert("กรุณากรอกประเภทของ  Bug ด้วย...");
		document.forms[0].typeName.forcus();
	}else{
	    document.forms[0].action="<%=request.getContextPath()%>/typeAddAction.dog";
	    document.forms[0].submit();
	}
    
  } 
 
 </script>
</head>
<body>



<form:form method="post" action="typeAddAction.dog">
  <table width="600px">
   
    <tr>
      <th>&nbsp; ประเภทของ Bug:
        <br/>
        <form:input path="typeName" size="45"/><font color="#FF0000">&nbsp;*</font> 
      </th>
    </tr> 
    
    <tr>
      <th>
      &nbsp; รายละเอียด :
        <br/>
        <form:textarea path="typeDesc" cols="35" rows="4"/> 
      </th>
    </tr>
    

    <tr>
      <td>     
         <p class="submit"><input type="button" value=" ADD " onclick="javascript:onSAVE();"/>&nbsp;&nbsp;<input type="reset" value=" Reset "/></p>
      </td>
    </tr>
  </table>
</form:form>
</body>
</html>







    