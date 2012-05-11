<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/template_includes.jsp" %>
<%@ include file="/jsp/template_header.jsp" %>
<title>ADD  type of Buger</title>
<script type="text/javascript">

function onSAVE() {
  
	if(document.forms[0].bName.value==''){
		alert("กรุณากรอก  ยี่ห้อสินค้าด้วย...");
		document.forms[0].dogSubject.focus();
	}else{
	    document.forms[0].action="<%=request.getContextPath()%>/BrandProductUpdateAction.dog";
	    document.forms[0].submit();
	}
    
  } 
 
 </script>
</head>
<body>
<div id="main">
<h4>ข้อมูลพื้นฐาน  -> <font color="#f26f21">เเก้ไข ยี่ห้อสินค้า</font></h4>

<form:form method="post" action="">
  <table width="600px">
    <form:hidden path="bId" />
     <tr>
      <th>&nbsp; รหัสยี่ห้อสินค้า :
          <form:input path="bId" size="20" disabled="true"/>
        <br/>
      </th>
    </tr>
    
    <tr>
      <th>&nbsp; ชื่อยี่ห้อสินค้า :
        <br/>
        <form:textarea path="bName" cols="35" rows="3"/> 
		<font color="#FF0000">&nbsp;*</font>Ex.Yazaki
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
    