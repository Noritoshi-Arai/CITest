<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.example.entity.RestEntity" %>
<% 
request.setCharacterEncoding("UTF-8");
%>
<%-- セッションからRestEntityオブジェクトを取得 --%>
<% 
RestEntity restEntity = (RestEntity) request.getAttribute("rest");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/Java_Architect_Spring/image/Login.css" type="text/css">
<title>休暇取得状況</title>
</head>
<body>
<u><h2>休暇取得状況</h2></u>
<div align="right"><a href="/Java_Architect_Spring/logout">ログアウト</a></div><br>
<div id="wrapper">
<div class="left-column">
メニュー<br>
・<a href="LoginServlet">トップページ</a><br>
・<a href="WorkScheduleServlet">出勤予定入力</a><br>
・<a href="ScheduleSearch.jsp">出勤予定検索</a><br>
・<a href="WorkResultInputServlet">勤務実績入力</a><br>
・<a href="RestServlet">休暇取得状況</a><br>
</div>
<div class="right-column">
<form action="/Java_Architect_Spring/rest" method="post">
<!--  <input type="hidden" name="page" value="change"> -->
<select name="year">
<option value="2020" 
<% 
if(restEntity.getYear() == 2020){
%>
selected
<% 
}
%>
>2020</option>
<option value="2021" 
<% 
if(restEntity.getYear() == 2021){
%>
selected
<% 
}
%>
>2021</option>
</select>
<input type="submit" value="日付変更">
</form>
<br><br>
<table border="1">
<tr>
	<th>名前</th>
	<th>取得可能通算日数</th>
	<th>残り取得必須日数</th>
</tr>
<tr>
	<td>${staffEntity.nameKanji}</td>
	<td><%= restEntity.getPossibleRest()%></td>
	<td><%= restEntity.getMustRest()%></td>
</tr>
</table>
<br><br>
<a href="ScheduleInput.jsp">出勤予定入力画面へ</a>
</div>
</div>
</body>
</html>