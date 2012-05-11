<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/template_includes.jsp" %>
<%@ include file="/jsp/template_header.jsp" %>
<title>Master Data of Merchant</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">

function onSAVE() {
  
	if(document.forms[0].typeName.value==''){
		alert("กรุณากรอก ประเภทสินค้าด้วย...");
		document.forms[0].typeName.focus();
	}else{
	    document.forms[0].action="<%=request.getContextPath()%>/TypeProductUpdateAction.dog";
	    document.forms[0].submit();
	}
    
  } 
 
 </script>
</head>
<body>
<div id="main">
<h4>ข้อมูลพื้นฐาน  -> <font color="#f26f21">เเก้ไข ประเภทสินค้า</font></h4>

<form:form method="post" action="">
  <table width="600px">
    <form:hidden path="typeId" />
     <tr>
      <th>&nbsp; รหัสประเภทสินค้า :
          <form:input path="typeId" size="20" disabled="true"/>
        <br/>
      </th>
    </tr>
    
    <tr>
      <th>&nbsp; ชื่อประเภทสินค้า :
        <br/>
        <form:textarea path="typeName" cols="35" rows="3"/> 
		<font color="#FF0000">&nbsp;*</font>
      </th>
    </tr> 


    <tr>
      <td>     
         <p class="submit"><input type="button" value=" EDIT " onclick="javascript:onSAVE();"/>&nbsp;&nbsp;<input type="reset" value=" Reset "/></p>
      </td>
    </tr>
  </table>
</form:form>

<%@ include file="/jsp/template_footer.jsp" %>
    