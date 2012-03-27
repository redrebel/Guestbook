<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import="kame.chap13.model.Message"%>
<%@ page import="kame.chap13.model.MessageListView"%>
<%@ page import="kame.chap13.service.GetMessageListService"%>
<%
	String pageNumberStr = request.getParameter("page");
	int pageNumber = 1;
	if(pageNumberStr != null) 
	{
		pageNumber = Integer.parseInt(pageNumberStr);
	}
	
	GetMessageListService messageListService = GetMessageListService.getInstance();
	MessageListView viewData = messageListService.getMessageList(pageNumber);
%>

<html>
<head>
	<title> 방명록 메시지 목록 </title>
</head>
<body>
<form action="writeMessage.jsp" method="post">
이름 : <input type="text" name="guestName" /><br/>
암호 : <input type="password" name="password"/><br/>
메시지 : <textarea name="message" cols="30" row="3"></textarea><br/>
<input type="submit" value="메시지 남기기"/>
</form>
<hr>

<% if(viewData.isEmpty()) { %>
등록된 메지시가 없습니다.
<% } else { /* 메시지 있는 경우 처리 시작 */ %>
<table border="1">
<%
	for(Message message : viewData.getMessageList()) {
%>
	<tr>
		<td>
		메시지 번호 : <%= message.getId() %> <br/>
		손님 이름 : <%= message.getGuestName() %><br/>
		메시지 : <%= message.getMessage() %> <br/>
		<a href="confirmDeletion.jsp?messageId=<%= message.getId() %>">
		[삭제하기]</a>
		</td>
	</tr>
<% } %>
</table>

<% for(int i = 1; i <= viewData.getPageTotalCount(); i++) { %>
<a href="list.jsp?page=<%= i %>">[<%= i %>]</a>
<% 	} %>
<% } /* 메시지 있는 경우 처리 끝 */ %>
</body>
</html>