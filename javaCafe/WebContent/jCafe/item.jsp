<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>jCafe/item.jsp</title>

<%

	String itemNo = request.getParameter("itemNo");
	
%>

<script>

	$(function () {
		$.ajax({
			url: '../getProductServlet',
			data:{item_no: "<%=itemNo%>"},
			dataType: 'json',
			success: function(result) {
				console.log(result);
				$('#item_no').val(result.item_no);
				$('#item').val(result.item);
			},
			error: function(result) {
				console.log(result);
			}
		});
		
		$('#btnChange').on('click', function() {
			
		})
	})

</script>
</head>
<body>
	<form>
		<input type="text" id="item_no" name="item_no">
		<input tyep="text" id="item" name="item">
		<input type="submit" id="btnChange" value="수정">
	</form>
</body>
</html>