<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/template_includes.jsp" %>
<%@ include file="/jsp/template_header.jsp" %>
<title>ADD  type of Buger</title>
<script type="text/javascript">

function onSAVE() {
  
	if(document.forms[0].dogSubject.value==''){
		alert("กรุณากรอก  หัวข้อ/ชื่อเรื่อง ด้วย...");
		document.forms[0].dogSubject.focus();
	}else if(document.forms[0].dogTypeIdDDL.value==''){		
		alert("กรุณาเลือก ประเภท Bug ด้วย...");
		document.forms[0].dogTypeIdDDL.focus();		
	}else if(document.forms[0].dogSolution.value==''){
		alert("กรุณากรอกแนวทางแก้ปัญหาหรือ เทคนิค ต่างๆ  ด้วย...");
		document.forms[0].dogSolution.focus();	
	}else{
	    document.forms[0].action="<%=request.getContextPath()%>/saveDoger.dog";
	    document.forms[0].submit();
	}
    
  } 
 
 </script>
</head>
<body>
  <div id="main">
<h4>ข้อมูลพื้นฐาน ประเภทสิ้นค้า</h4>

<form:form method="post" action="">
  <table width="600px">
   
     <tr>
      <th>&nbsp; รหัสประเภทสินค้า :
        <br/>
      </th>
    </tr>
    
    <tr>
      <th>&nbsp; ชื่อประเภทสินค้า :
        <br/>
        <form:textarea path="dogSubject" cols="35" rows="3"/> 
		<font color="#FF0000">&nbsp;*</font>Ex.เครื่องใช้ไฟฟ้า 
      </th>
    </tr> 


    <tr>
      <td>     
         <p class="submit"><input type="button" value=" ADD " onclick="javascript:onSAVE();"/>&nbsp;&nbsp;<input type="reset" value=" Reset "/></p>
      </td>
    </tr>
  </table>
</form:form>

<%@ include file="/jsp/template_footer.jsp" %>
    