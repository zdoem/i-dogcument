<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/template_includes.jsp" %>
<%@ include file="/jsp/template_header.jsp" %>
<title>Master Data of Merchant</title>
<%
 String contextPath = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=contextPath %>/ui-lightness/jquery.ui.all.css">
	<script src="<%=contextPath %>/js/jquery-1.5.1.js"></script>
	<script src="<%=contextPath %>/ui/jquery.ui.core.js"></script>
	<script src="<%=contextPath %>/ui/jquery.ui.widget.js"></script>
	<script src="<%=contextPath %>/ui/jquery.ui.datepicker.js"></script>
	<link rel="stylesheet" href="<%=contextPath %>/ui-lightness/jquery-ui-1.8.14.custom.css">
<script>
	$(function() {
		//$( "#datepicker" ).datepicker();	
		$( "#startDate" ).datepicker({ dateFormat: 'yy-mm-dd' });
	});
	
	$(function() {
		//$( "#datepicker2" ).datepicker();
		$( "#stopDate" ).datepicker({ dateFormat:  'yy-mm-dd'  });
	});
	
	/*function test(){	
		var date1 = document.getElementById("datepicker").value;
		var date2 = document.getElementById("datepicker2").value;		
		alert("Date1:"+date1+"\n Date2:"+date2);
	}*/
</script>
<script type="text/javascript">

function onSAVE() {
	if(document.forms[0].productName.value==''){
		alert("กรุณากรอก  ชื่อสินค้าด้วย...");
		document.forms[0].productName.focus();
	}else if(document.forms[0].brandProductIdDDL.value==''){
		alert("กรุณากรอก  ยี่ห้อสินค้า ด้วย...");
		document.forms[0].brandProductIdDDL.focus();
	}else if(document.forms[0].typeProductIdDDL.value==''){
		alert("กรุณากรอก  ประเภทสินค้าด้วย...");
		document.forms[0].typeProductIdDDL.focus();
	}else if(document.forms[0].priceCost.value=='' ||
			document.forms[0].priceCost.value<=0){
		alert("กรุณากรอก  ราคาต้นทุน ด้วย...");
		document.forms[0].priceCost.focus();
	}else if(document.forms[0].priceSale.value=='' ||
		document.forms[0].priceSale.value <= 0){
		alert("กรุณากรอก  ราคาขายด้วย...");
		document.forms[0].priceSale.focus();
	}else if(document.forms[0].productItem.value=='' ||
			document.forms[0].productItem.value <= 0){
		alert("กรุณากรอก  จำนวนสินค้าด้วย...");
		document.forms[0].productItem.focus();
	}
	
	/*else if(document.forms[0].priceCost.value==''){
		alert("กรุณากรอก  วันที่ผลิดด้วย...");
		document.forms[0].priceCost.focus();
	} 
	
	if(document.forms[0].priceCost.value==''){
		alert("กรุณากรอก  วันที่หมดอายุด้วย...");
		document.forms[0].priceCost.focus();
	}*/
	
	else{
	    document.forms[0].action="<%=request.getContextPath()%>/ProductUpdateAction.dog";
	    document.forms[0].submit();
	}
  } 
 
 </script>
</head>
<body>

<div id="main">
<h4>ข้อมูลพื้นฐาน  -> <font color="#f26f21">เพิ่ม สินค้า</font></h4>

<form:form method="post" action="" name="frm1" >
<form:hidden path="productId" />
  <table width="600px">
      <tr>
      <th>&nbsp; รหัสสินค้า : 
        <br/>
		<form:input path="productId" size="45" disabled="true"/>
		<font color="#FF0000">&nbsp;*</font>
      </th>
      </tr>
   
        <tr>
      <th>&nbsp; ชื่อสินค้า :
        <br/>
		<form:input path="productName" size="45"/>
		<font color="#FF0000">&nbsp;*</font>
      </th>
    </tr> 
       <tr>
      <th>&nbsp; ยี่ห้อสินค้า :
        <br/>
		  <form:select path="brandProductIdDDL">
                   <form:option value="">---Select---</form:option>
                   <form:options items="${bProductListDDL}" />
          </form:select>
      </th>
    </tr> 
    
    
     <tr>
      <th>&nbsp; ประเภทสินค้า:
        <br/>
		 <form:select path="typeProductIdDDL">
                   <form:option value="">---Select---</form:option>
                    <form:options items="${tProductListDDL}" />
          </form:select>
      </th>
    </tr> 
    
    
    <tr>
      <th>&nbsp; ราคาต้นทุน :
        <br/>
		<form:input path="priceCost" size="25"/>
		<font color="#FF0000">&nbsp;*</font>
      </th>
    </tr> 
    
        <tr>
      <th>&nbsp; ราคาขาย:
        <br/>
		<form:input path="priceSale" size="25"/>
		<font color="#FF0000">&nbsp;*</font>
      </th>
    </tr> 
    
        <tr>
      <th>&nbsp; จำนวน :
        <br/>
		<form:input path="productItem" size="25"/>
		<font color="#FF0000">&nbsp;*</font>
      </th>
    </tr> 
    
     <tr>
      <th>&nbsp; วันที่ผลิต:
        <br/>
		<form:input path="productDateStart" size="25" id="startDate"/>
      </th>
    </tr> 
    
         <tr>
      <th>&nbsp; วันที่หมดอายุ:
        <br/>
		<form:input path="productDateExpire" size="25" id="stopDate"/>
      </th>
    </tr> 
    
     <tr>
      <th>&nbsp; รายละเอียด:
        <br/>
		<form:textarea path="productDesc" cols="35" rows="3"/> 
		<font color="#FF0000">&nbsp;</font>
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
    