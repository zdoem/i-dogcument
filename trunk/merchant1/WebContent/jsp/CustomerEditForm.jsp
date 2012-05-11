<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/template_includes.jsp" %>
<%@ include file="/jsp/template_header.jsp" %>
<title>Master Data of Merchant</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">

function onSAVE() {
  

	if(document.forms[0].fName.value==''){
		alert("กรุณากรอก  ชื่อลูกค้าด้วย...");
		document.forms[0].fName.focus();
	}else 	if(document.forms[0].lName.value==''){
		alert("กรุณากรอก  นามสกุลลูกค้าด้วย...");
		document.forms[0].lName.focus();
	}else{
	    document.forms[0].action="<%=request.getContextPath()%>/CustomerUpdateAction.dog";
	    document.forms[0].submit();
	}
    
  } 
 
 </script>
</head>
<body>
<div id="main">
<h4>ข้อมูลพื้นฐาน  -> <font color="#f26f21">แก้ไขลูกค้า</font></h4>

<form:form method="post" action="">
   <form:hidden path="id" />
  <table width="600px">
   
     <tr>
      <th>&nbsp; รหัสลูกค้า :
        <br/>
        <form:input path="id" size="20" disabled="true"/>
      </th>
    </tr>
    
        <tr>
      <th>&nbsp; ชื่อ :
        <br/>
        <form:input path="fName" size="45"/>
		<font color="#FF0000">&nbsp;*</font>
      </th>
    </tr> 
    
    <tr>
      <th>&nbsp; นามสกุล :
        <br/>
         <form:input path="lName" size="45"/>
		<font color="#FF0000">&nbsp;*</font>
      </th>
    </tr> 
    
     <tr>
      <th>&nbsp; เบอร์บ้าน:
        <br/>
         <form:input path="tel" size="45"/>
      </th>
    </tr> 
    
         <tr>
      <th>&nbsp; เบอร์โทร:
        <br/>
        <form:input path="mobile" size="45"/>
      </th>
    </tr> 
    
     <tr>
      <th>&nbsp; E-mail:
        <br/>
        <form:input path="email" size="45"/>
		<font color="#FF0000">&nbsp;*</font>Ex.dddd@ddd.com
      </th>
    </tr> 
    
      <tr>
      <th>&nbsp; ที่อยุ่ :
        <br/>
        <form:textarea path="address" cols="35" rows="3"/> 
      </th>
    </tr> 
    
    
     <tr>
      <th>&nbsp; คำอธิบาย:
        <br/>
        <form:textarea path="comment" cols="35" rows="3"/> 
      </th>
    </tr> 
    


    <tr>
      <td>     
         <p class="submit"><input type="button" value=" EDIT " onclick="javascript:onSAVE();"/>&nbsp;&nbsp;<input type="reset" value=" Reset "/></p>
      </td>
    </tr>
  </table>
</form:form>
</div>
<%@ include file="/jsp/template_footer.jsp" %>
    