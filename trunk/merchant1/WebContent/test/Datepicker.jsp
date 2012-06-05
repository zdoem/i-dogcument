<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Date Pickert by Jquery</title>

<link rel="stylesheet" href="../ui-lightness/jquery.ui.all.css">
	<script src="../js/jquery-1.5.1.js"></script>
	<script src="../ui/jquery.ui.core.js"></script>
	<script src="../ui/jquery.ui.widget.js"></script>
	<script src="../ui/jquery.ui.datepicker.js"></script>
	<link rel="stylesheet" href="../ui-lightness/jquery-ui-1.8.14.custom.css">
<script>
	$(function() {
		//$( "#datepicker" ).datepicker();	
		$( "#datepicker" ).datepicker({ dateFormat: 'dd-mm-yy' });
	});
	
	$(function() {
		//$( "#datepicker2" ).datepicker();
		$( "#datepicker2" ).datepicker({ dateFormat:  'dd-mm-yy'  });
	});
	
	function test(){
		
		var date1 = document.getElementById("datepicker").value;
		var date2 = document.getElementById("datepicker2").value;
		
		alert("Date1:"+date1+"\n Date2:"+date2);
	}
	</script>
</head>
<body>

<form name="frm1">
<div class="demo">
<p>Date1: <input type="text" id="datepicker"> &nbsp; TO Date2: <input type="text" id="datepicker2"></p>
</div>
<input type="button" name="submit" value="submit" onclick="test();"/>
</form>	
<!-- End demo -->


</body>
</html>