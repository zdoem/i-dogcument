<%@ include file="/jsp/template_includes.jsp" %>
<%@ include file="/jsp/template_header.jsp" %>
<%@ include file="/jsp/template_includes2.jsp" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Master Data of Merchant</title>
<script type="text/javascript">
function  doEdit(id){
    //document.forms[0].cmd.value="load";	   
    document.forms[0].action="<%=request.getContextPath()%>/CustomerRetrieve.dog?cId="+id;
    document.forms[0].submit();
}

function doDelete(id){
	if(confirm("คุณต้องการลบรายการ  ID  "+id+" ใช่หรือไม่?")){
			document.forms[0].action="<%=request.getContextPath()%>/CustomerDelete.dog?cId="+id;
    		document.forms[0].submit();
	}
}

function onADD(){
	document.forms[0].action="<%=request.getContextPath()%>/CustomerAddForm.dog";
    document.forms[0].submit();
}
</script>
</head>
<body>
<br>
<br>

<center>
<%-- // use our pagedListHolder --%>
<jsp:useBean id="pagedListHolder" scope="request" type="org.springframework.beans.support.PagedListHolder"/>
<%-- // create link for pages, "~" will be replaced later on with the proper page number --%>
<c:url value="/CustomerList.dog" var="pagedLink">
	<c:param name="action" value="CustomerList.dog"/>
    <c:param name="p" value="~"/>
</c:url>

<%-- // load our paging tag, pass pagedListHolder and the link --%>
<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>

<%-- // show only current page worth of data --%>
<form:form  commandName="CustomerForm" method="POST" action="CustomerList.dog">
<input type="hidden" name="cmd" value="">
<table width="800px" border="1" bordercolor="#FFFFFF">
<tr><td colspan="5" align="left"><h4>ข้อมูลพื้นฐาน  -> <font color="#f26f21">รายการชื่อลูกค้า</font></h4></td>
</tr>
<tr bgcolor="#497c09">
	<th style="color:#FFFFFF;font-weight:bold;text-align:center" width="10%">CUSTOMER_ID</th>
	<th style="color:#FFFFFF;font-weight:bold;text-align:center">CUSTOMER_NAME</th>
	<th style="color:#FFFFFF;font-weight:bold;text-align:center">CONTACT</th>
	<th style="color:#FFFFFF;font-weight:bold;text-align:center">E-Mail</th>
	<th style="color:#FFFFFF;font-weight:bold;text-align:center">ADDRESS</th>
	<th style="color:#FFFFFF;font-weight:bold;text-align:center" width="5%">EDIT</th>
	<th style="color:#FFFFFF;font-weight:bold;text-align:center" width="5%">DELETE</th>
</tr>


<c:forEach items="${pagedListHolder.pageList}" var="item">
    <tr height="20px">
    	<td align="center">${item.cusId}</td>
    	<td>${item.cusFname}&nbsp;&nbsp;${item.cusFname}</td>
    	<td>${item.cusTel},${item.cusMobile}</td>
    	<td>${item.cusEmail}</td>
    	<td>${item.cusAddress}</td>
    	<td><a href="javascript:doEdit('${item.cusId}')">Edit</a></td>
    	<td><a href="javascript:doDelete('${item.cusId}')">Delete</a></td>
    </tr>
</c:forEach>
<tr><td colspan="5" align="left" >
<p class="submit"><input type="button" value=" ADD " onclick="javascript:onADD();"/></p>
</td>
</tr>
</table>

</form:form>

<%-- // load our paging tag, pass pagedListHolder and the link --%>
<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>

</body>
</html>
<%@ include file="/jsp/template_footer.jsp" %>
</center>