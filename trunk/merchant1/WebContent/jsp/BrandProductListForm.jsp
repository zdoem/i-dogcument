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
    document.forms[0].action="<%=request.getContextPath()%>/BrandProductRetrieveRec.dog?bId="+id;
    document.forms[0].submit();
}

function doDelete(id){
	if(confirm("คุณต้องการลบรายการ  ID  "+id+" ใช่หรือไม่?")){
			document.forms[0].action="<%=request.getContextPath()%>/BrandProductDelete.dog?pId="+id;
    		document.forms[0].submit();
	}
}

function onADD(){
	document.forms[0].action="<%=request.getContextPath()%>/BrandProductAddForm.dog";
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
<c:url value="/BrandProductList.dog" var="pagedLink">
	<c:param name="action" value="BrandProductList.dog"/>
    <c:param name="p" value="~"/>
</c:url>

<%-- // load our paging tag, pass pagedListHolder and the link --%>
<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>

<%-- // show only current page worth of data --%>
<form:form  commandName="Type" method="POST" action="BrandProductList.dog">
<input type="hidden" name="cmd" value="">
<table width="800px" border="1" bordercolor="#FFFFFF">
<tr><td colspan="5" align="left"><h4>รายการข้อมูลพื้นฐาน   ยี่ห้อสินค้า</h4></td>
</tr>
<tr bgcolor="#497c09">
	<th style="color:#FFFFFF;font-weight:bold;text-align:center" width="10%">BRAND_ID</th>
	<th style="color:#FFFFFF;font-weight:bold;text-align:center">BRAND_NAME</th>
	<th style="color:#FFFFFF;font-weight:bold;text-align:center" width="5%">EDIT</th>
	<th style="color:#FFFFFF;font-weight:bold;text-align:center" width="5%">DELETE</th>
</tr>
<c:forEach items="${pagedListHolder.pageList}" var="item">
    <tr height="20px">
    	<td align="center">${item.bId}</td>
    	<td>${item.bName}</td>
    	<td><a href="javascript:doEdit('${item.bId}')">Edit</a></td>
    	<td><a href="javascript:doDelete('${item.bId}')">Delete</a></td>
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