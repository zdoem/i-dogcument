<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/template_includes.jsp" %>
<%@ include file="/jsp/template_header.jsp" %>
<title>Master Data of Merchant</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

function onSAVE() {
  
	if(document.forms[0].typeName.value==''){
		alert("กรุณากรอก  ประเภทสินค้าด้วย...");
		document.forms[0].typeName.focus();
	}else{
	    document.forms[0].action="<%=request.getContextPath()%>/TypeProductSaveAction.dog";
	    document.forms[0].submit();
	}
    
  } 
 
 </script>
</head>
<body>
<div id="main">
<h4>ข้อมูลพื้นฐาน  -> <font color="#f26f21">เพิ่ม ประเภทสินค้า</font></h4>

<form:form method="post" action="">
  <table width="600px">
   
     <tr>
      <th>&nbsp; รหัสประเภทสินค้า : Auto Generate
        <br/>
      </th>
    </tr>
    
    <tr>
      <th>&nbsp; ชื่อประเภทสินค้า :
        <br/>
        <form:textarea path="typeName" cols="35" rows="3"/> 
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
    