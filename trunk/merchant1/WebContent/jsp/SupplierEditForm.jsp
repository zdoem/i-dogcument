<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/template_includes.jsp" %>
<%@ include file="/jsp/template_header.jsp" %>
<title>Master Data of Merchant</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

function onSAVE() {
	if(document.forms[0].companyName.value==''){
		alert("กรุณากรอก ชื่อบริษัท  ด้วย...");
		document.forms[0].companyName.focus();
	}else if(document.forms[0].contactName.value==''){
		alert("กรุณากรอก  ชื่อ-นามสกุลผู้ติดต่อ ด้วย...");
		document.forms[0].contactName.focus();
	}else{
	    document.forms[0].action="<%=request.getContextPath()%>/SupplierUpdateAction.dog";
	    document.forms[0].submit();
	}
  } 
 
 </script>
</head>
<body>

<div id="main">
<h4>ข้อมูลพื้นฐาน  -> <font color="#f26f21">เเก้ไข  Supplier </font></h4>

<form:form method="post" action="" >
  <form:hidden path="id"/>
  <table width="600px">
   
      <tr>
      <th>&nbsp; ชื่อบริษัท :
        <br/>
		<form:input path="companyName" size="45"/>
		<font color="#FF0000">&nbsp;*</font>
      </th>
    </tr> 
    
    <tr>
      <th>&nbsp; ชื่อ-นามสกุล Supplier :
        <br/>
		<form:input path="contactName" size="45"/>
		<font color="#FF0000">&nbsp;*</font>
      </th>
    </tr> 
    
        <tr>
      <th>&nbsp; E-mail:
        <br/>
		<form:input path="email" size="45"/>
		<font color="#FF0000">&nbsp;*</font>
      </th>
    </tr> 
    
        <tr>
      <th>&nbsp; Tel :
        <br/>
		<form:input path="tel" size="45"/>
		<font color="#FF0000">&nbsp;*</font>
      </th>
    </tr> 
    
     <tr>
      <th>&nbsp; Mobile:
        <br/>
		<form:input path="mobile" size="45"/>
      </th>
    </tr> 
    
      <tr>
      <th>&nbsp; ที่อยุ่ :
        <br/>
		 <form:textarea path="address" cols="35" rows="3"/> 
      </th>
    </tr> 
    
     <tr>
      <th>&nbsp; รายละเอียดเพิ่มเติม:
        <br/>
		 <form:textarea path="desc" cols="35" rows="3"/> 
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
    