<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>选择城市--Tuan ++</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
<script type="text/javascript">
$(function(){
	$('#cityModal').modal({remote:"<%=request.getContextPath()%>/ajax/getAllCity"});
});
</script>
</body>
</html>