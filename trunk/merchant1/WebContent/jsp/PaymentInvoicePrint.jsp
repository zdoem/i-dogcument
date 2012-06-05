<%@ include file="/jsp/template_includes.jsp" %>
<%@ include file="/jsp/template_header.jsp" %>
<%@ include file="/jsp/template_includes2.jsp" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<%@page import="java.util.List" %>	
<%@page import="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Master Data of Merchant</title>
<script type="text/javascript">

function onPrint(invoice){
	document.forms[0].action="<%=request.getContextPath()%>/TransPayinPrintAction.dog?invoiceId="+invoice;
    document.forms[0].submit();
}
</script>

</head>

<%
String contextPath = request.getContextPath();
%>

<body>
<br>
<br>
<center>
<%-- // show only current page worth of data
<form:form  commandName="ProductForm" method="POST" action="ProductList.dog">
 --%>
 
<form:form  method="POST" action="">
<input type="hidden" name="invoiceId" value="<%=request.getAttribute("invoice_ref")%>">
<table width="800px" border="1" bordercolor="#FFFFFF">
<tr><td  align="left"><h4>PAYMENT FORM -> <font color="#f26f21">PAYIN</font></h4></td>
</tr>
<td >
	<table width="800px" border="2" bordercolor="#FFFFFF">
	<tr>
	<td valign="middle"   style="color:#000000;font-weight:bold;text-align:left">	
	&nbsp; แสดงความยินดีด้วยท่านทำรายการเรียบร้อยแล้วค่ะ  ????
	</td>	
	</tr>	
	</table>		
</td>      
</tr> 
<tr><td align="right" >
<p class="submit" >
&nbsp;&nbsp;<input type="button" value=" PRINT-INVOICE " onclick="javascript:onPrint(<%=request.getAttribute("invoice_ref")%>);"/></p>
</td>
</tr>
</table>

</form:form>


</body>
</html>
<%@ include file="/jsp/template_footer.jsp" %>
</center>