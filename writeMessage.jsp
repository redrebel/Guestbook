<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page errorPage="errorView.jsp" %>
<%@ page import="kame.chap13.model.Message" %>
<%@ page import="kame.chap13.service.WriteMessageService" %>
<%
	request.setCharacterEncoding("euc-kr");
%>
<jsp:useBean id="message" class="kame.chap13.model.Message">
	<jsp:setProperty name="message" property="*" />
</jsp:useBean>
<%
	WriteMessageService writeService = WriteMessageService.getInstance();
	writeService.write(message);
%>
<html>
<head>
	<title>���� �޽��� ����</title>
</head>
<body>
���Ͽ� �޽����� ������ϴ�.
<br/>
<a href="list.jsp">[��� ����]</a>
</body>
</html>