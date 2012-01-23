<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="/jsp/template_includes.jsp" %>
<%@ include file="/jsp/template_header.jsp" %>
<script language="javascript">
</script>
</head>
<body>
 <div id="main">
<img src="images/pets.png" align="right" style="position:relative;right:30px;">
<h2>Welcome 'Watch-dog' System</h2>
<P><b>&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; 'Watch-dog' คืออะไร   <br>
 Watch คือ ดู,เฝ้าดู,จับตา  <br>
 Dog มาจากคำว่า Log หรือ Logger    <br>
ดังนั้นจะแปลได้ว่า เป็นการดู logger หรือ อาจจะเป็น trick ,Errors ต่างๆ  <br>
สำหรับช่วยให้เราดูย้อนหลัง หรือ ที่เราประสบพบเจอในการเขียนโปรแกรม ,coding,<br>
 ปัญหาในการ  intallation หรือ configuration ต่างๆ  <br>
เพื่อเป็นเครื่องเมือช่วยจำให้เรา ค้นหาปัญหานั้นๆเพื่อนำไปแก้ไขได้อย่างรวดเร็ว  <br>  
ช่วยลดเวลาในการทำงาน มีเวลามากขึ้น

</b></P>

<ul>
  <li><a href="<c:url value="/typeList.dog"/>">ข้อมูลพื้นฐาน  ประเภท  Bug</a></li>
  <li><a href="<c:url value="/dogList.dog"/>">List Watch dog</a></li>
  <br></br>

  <br></br>
</ul>
<br style="font-size:5pt">
<br style="font-size:5pt">
<br style="font-size:5pt">

<p>&nbsp;</p>

<%@ include file="/jsp/template_footer.jsp" %>
