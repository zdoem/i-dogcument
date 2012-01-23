<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/template_includes.jsp" %>
<%@ include file="/jsp/template_header.jsp" %>
<title>Message from Watchdog</title>
 <meta http-equiv="content-type" content="text/html; charset=utf-8" />
 <%
     String msg = request.getAttribute("msg")==null?"":request.getAttribute("msg").toString();
     String url = request.getAttribute("url")==null?"":request.getAttribute("url").toString();   
     String errorCode = request.getParameter("error")==null?"0":request.getParameter("error");  

     url = request.getContextPath()+"/"+url;
 %>
 
<script type="text/javascript">
function onOkay() {
     	frmMsg.action = "<%=url%>";
	    frmMsg.submit();
}
</script>
 
<%
	String context = request.getContextPath();
%>
<br>

<h2>&nbsp;</h2>
<center>
<form action="<%=url %>" method="post" name="frmMsg" width="800px" align="center">
<table width="400" border="1"  celpadding="0" celspacing="0" bordercolor="#009933" align="center">
<tr> 
     <td bgcolor="#7db223"><div style="color:#FFFFFF;font-size:14px; font-family:Arial; font-weight:bold;"><b>Message Result..</b></div></td>
</tr>
<tr> 
     <td bgcolor="#ece9d8" style="color:#006600; font-size:12px; font-family:Arial; font-weight:bold;">
      <center>
      <br>
       <img src="<%=context %>/images/happy.png" width="100" height="100" border="0">
	  </center>
	  <br>
	  <h4> ${msg}</h4>
		<p class="submit" align="right" >
	    <input type="button" value=" Okay " onclick="javascript:onOkay();"/>
      </td>
</tr>
</table>
</form>
<br>

<%@ include file="/jsp/template_footer.jsp" %>
</center>