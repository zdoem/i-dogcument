<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="/jsp/template_includes.jsp" %>
<%@ include file="/jsp/template_header.jsp" %>
<script language="javascript">
</script>
</head>
<body>
 <div id="main">
<img src="images/pets.png" align="right" style="position:relative;right:30px;">
<h2>Welcome 'test' System</h2>
<P><b>&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; 'test'  xxxx <br>


</b></P>

<ul>
   <li><a href="<c:url value="/test.dog"/>">Test Blank Spring</a></li>
   <br>
  <li><a href="<c:url value="/BrandProductAddForm.dog"/>">ข้อมูลพื้นฐาน  ยี่ห้อสินค้า</a></li>
  <li><a href="<c:url value="/BrandProductList.dog"/>">รายการยี่ห้อสินค้า</a></li>
  <hr>
    <li><a href="<c:url value="/TypeProductAddForm.dog"/>">ข้อมูลพื้นฐาน  ประเภทสินค้า</a></li>
  <li><a href="<c:url value="/TypeProductList.dog"/>">รายการ ประเภทสินค้า</a></li>
  
   <hr>
   <li><a href="<c:url value="/CustomerAddForm.dog"/>">ข้อมูลพื้นฐาน  เพิ่มชื่อลูกค้า</a></li>
  <li><a href="<c:url value="/CustomerList.dog"/>">รายการ รายการชื่อลูกค้า</a></li>
   
   <hr>
   <li><a href="<c:url value="/UsersAddForm.dog"/>">ข้อมูลพื้นฐาน  เพิ่ม User</a></li>
  <li><a href="<c:url value="/UsersList.dog"/>">รายการ รายการ User</a></li>
  
  
  <br></br>
  <br></br>

  <br></br>
</ul>
<br style="font-size:5pt">
<br style="font-size:5pt">
<br style="font-size:5pt">

<p>&nbsp;</p>

<%@ include file="/jsp/template_footer.jsp" %>
