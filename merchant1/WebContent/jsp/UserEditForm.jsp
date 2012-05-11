<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/template_includes.jsp" %>
<%@ include file="/jsp/template_header.jsp" %>
<title>Master Data of Merchant</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

function onSAVE() {
	if(document.forms[0].userName.value==''){
		alert("กรุณากรอก  User Name ด้วย...");
		document.forms[0].userName.focus();
	}else if(document.forms[0].password.value==''){
		alert("กรุณากรอก  Password ด้วย...");
		document.forms[0].password.focus();
	}else if(document.forms[0].fName.value==''){
		alert("กรุณากรอก  ชื่อด้วย...");
		document.forms[0].fName.focus();
	}else 	if(document.forms[0].lName.value==''){
		alert("กรุณากรอก  นามสกุลด้วย...");
		document.forms[0].lName.focus();
	}else{
	    document.forms[0].action="<%=request.getContextPath()%>/UsersUpdateAction.dog";
	    document.forms[0].submit();
	}
  } 
 
 </script>
</head>
<body>

<div id="main">
<h4>ข้อมูลพื้นฐาน  -> <font color="#f26f21">เเก้ไข  User</font></h4>

<form:form method="post" action="" >
   <form:hidden path="usrId" />
  <table width="600px">
       <tr>
      <th>&nbsp; ชื่อ :
        <br/>
		<form:input path="usrId" size="45"  disabled="true"/>
		<font color="#FF0000">&nbsp;*</font>
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
      <th>&nbsp; User Name:
        <br/>
		<form:input path="userName" size="45"/>
		<font color="#FF0000">&nbsp;*</font>
      </th>
    </tr> 
    
        <tr>
      <th>&nbsp; Password :
        <br/>
		<form:password path="password" size="45"/>
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
		<font color="#FF0000">&nbsp;</font>Ex.dddd@ddd.com
      </th>
    </tr> 
    
      <tr>
      <th>&nbsp; ที่อยุ่ :
        <br/>
		 <form:textarea path="address" cols="35" rows="3"/> 
      </th>
    </tr> 
    
    
     <tr>
      <th>&nbsp; เลขที่บัตรประจำตัวประชาชน:
        <br/>
		<form:input path="citizenId" size="45"/>
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
    