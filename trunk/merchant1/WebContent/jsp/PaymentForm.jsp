<%@ include file="/jsp/template_includes.jsp" %>
<%@ include file="/jsp/template_header.jsp" %>
<%@ include file="/jsp/template_includes2.jsp" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<%@page import="java.util.List" %>	
<%@page import="java.util.*" %>
<%@page import="com.ipro.model.bean.ProductBean" %>	
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Master Data of Merchant</title>
<script type="text/javascript">

function onPayIn(){
	document.forms[0].action="<%=request.getContextPath()%>/TransPayinAction.dog";
    document.forms[0].submit();
}
</script>

<script type="text/javascript">
function Calulate(total){
	var amount  = parseFloat(document.forms[0].payAmount.value);
	var vTotal = parseFloat(total);
    var resultAmount = 0;
   
  //if(parseFloat(amount) < parseFloat(vTotal)){
		//alert("จำนวนเงินชำระสินค้าไม่พอจ่าย กรุณาระบบจำนวนเงินชำระสินค้าใหม่ ..");
		//document.forms[0].payAmount.focus();
		//document.forms[0].payAmount.select();
	//}esle{		
		resultAmount = parseFloat(amount)-vTotal;
		document.getElementById('result').innerHTML = addCommas(resultAmount.toFixed(2)); 
	//}
}

function addCommas(nStr){
	nStr += '';
	x = nStr.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	}
	return x1 + x2;
}
</script>
</head>

<%
ArrayList list =(ArrayList)request.getAttribute("arrOrderList");
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
<input type="hidden" name="inVoidId" value="<%=request.getAttribute("inVoidId")%>">
<table width="800px" border="1" bordercolor="#FFFFFF">
<tr><td colspan="9" align="left"><h4>PAYMENT FORM -> <font color="#f26f21">PAYIN</font></h4></td>
</tr>

<tr bgcolor="#497c09">
	<th style="color:#FFFFFF;font-weight:bold;text-align:center" width="10%">รหัสสินค้า</th>
	<th style="color:#FFFFFF;font-weight:bold;text-align:center">ชื่อสินค้า</th>	
	<th style="color:#FFFFFF;font-weight:bold;text-align:center" >จำนวนสินค้า</th>	
	<th style="color:#FFFFFF;font-weight:bold;text-align:center">ราคาขาย</th>
	<th style="color:#FFFFFF;font-weight:bold;text-align:center">รวมเป็นเงิน</th>
</tr>
<%  
   float total = 0.0f;
   int sumItems = 0;
   float sumSale = 0.0f;
   int i = 0;
   if(list!=null &&list.size()> 0){
	    Iterator it = list.iterator();
	     while(it.hasNext()){
	    	 float vSum = 0.0f;
	    	 ProductBean obj =(ProductBean)it.next();
	         i++;
	         sumSale += obj.getPriceSale();
	         vSum = obj.getProductItem() * obj.getPriceSale();	          
	         sumItems +=obj.getProductItem();
	         total += vSum;
%>

    <tr height="20px">
    	<td align="center"><%=obj.getProductId() %></td>
    	<td><%=obj.getProductName() %></td>
    	<td align="center"><%=obj.getProductItem()%></td>
    	<td align="right"><%=obj.getPriceSale() %></td>    	
    	<td align="right"><%=vSum%></td>
    </tr>
<%
       } //End while
    	 %>
      <tr bgcolor="#fda240"> 
            <td  style="color:#FFFFFF;font-weight:bold;text-align:right"   colspan="2">&nbsp;รวมสุทธิ</td>		    	    
		    <td  style="color:#FFFFFF;font-weight:bold;text-align:center"   >&nbsp;<%=sumItems %>&nbsp;</td>
		    <td  style="color:#FFFFFF;font-weight:bold;text-align:right"   >&nbsp;<%=sumSale %>   </td>	
		    <td  style="color:#FFFFFF;font-weight:bold;text-align:right"  >&nbsp;<%=total %></td>
		  
	   </tr>
    <%  
       //End if
    }else{
  %>
  	   <tr>
		    <td align="center"  colspan="6">&nbsp;ยังไม่มีข้อมูล </td>
	   </tr>
  <%} %>
<tr>
<td colspan="9">

<table width="800px" border="2" bordercolor="#FFFFFF">
<tr>
<td valign="middle" width="220px"  style="color:#000000;font-weight:bold;text-align:left">	
จำนวนเงินที่ชำระสินค้า:
        <br/>
		<input name="payAmount"  id="payAmount" size="25" type="text" maxlength="10"   onblur="Calulate('<%=total%>')"/>
		<font color="#FF0000">&nbsp;*</font>
</td>	
<td valign="middle" style="color:#000000;font-weight:bold; font-size:18 ;text-align:left">		
		<p class="submit">เงินคงเหลือ  : <div id="result" style="color:#000000;font-weight:bold; color:RED; font-size:24 ;text-align:left">ทดสอบ </div></p>
</td>
</tr>	
</table>		
</td>      
</tr> 
<tr><td  colspan="9" align="right" >
<p class="submit" >
<input type="button" value=" PAY-LATER "  onclick="javascript:onPayLater();"/>
&nbsp;&nbsp;<input type="button" value=" PAY-IN " onclick="javascript:onPayIn();"/></p>
</td>
</tr>
</table>

</form:form>


</body>
</html>
<%@ include file="/jsp/template_footer.jsp" %>
</center>