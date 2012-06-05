<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="/jsp/template_includes.jsp" %>
<%@ include file="/jsp/template_header.jsp" %>
<script language="javascript">
</script>
</head>
<body>
 <div id="main">
<img src="<%=request.getContextPath() %>/images/pets.png" align="right" style="position:relative;right:30px;">
<h2>Welcome 'test' System</h2>
<P><b>&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; 'test'  xxxx <br>


</b></P>

<ul>
   <li>Menu Transaction && Report krub</li>
   <br>
  <li><a href="<c:url value="/TransOrderFormLoad.dog"/>">POST Product /Sale</a></li>
  <li><a href="<c:url value="/BrandProductList.dog"/>">PURCHASE Product/Orders</a></li>
   <li><a href="<c:url value="/TypeProductAddForm.dog"/>">CLAIM Product/Void</a></li>
  <li><a href="<c:url value="/TypeProductList.dog"/>">Reporting</a></li>
  

  
  <br></br>
  <br></br>

  <br></br>
</ul>
<br style="font-size:5pt">
<br style="font-size:5pt">
<br style="font-size:5pt">

<p>&nbsp;</p>

<%@ include file="/jsp/template_footer.jsp" %>
