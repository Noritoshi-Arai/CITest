<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.example.entity.WorkScheduleEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<% 
request.setCharacterEncoding("UTF-8");
%>
<%
List<WorkScheduleEntity> list = (List<WorkScheduleEntity>) request.getAttribute("searchResult");
%>
<%-- SimpleDateFormatの定義 --%>
<% 
	SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");	//年
	SimpleDateFormat msdf = new SimpleDateFormat("MM");		//月
	SimpleDateFormat dsdf = new SimpleDateFormat("dd");		//日
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/Java_Architect_Spring/image/Login.css" type="text/css">
<title>検索結果</title>
</head>
<body>
<h2>検索結果</h2>
<div align="right"><a href="/Java_Architect_Spring/logout">ログアウト</a></div><br>
<div id="wrapper">
<div class="left-column">
メニュー<br>
・<a href="/Java_Architect_Spring/toppage">トップページ</a><br>
・<a href="/Java_Architect_Spring/scheduleInput">出勤予定入力</a><br>
・<a href="/Java_Architect_Spring/scheduleSearch">出勤予定検索</a><br>
・<a href="/Java_Architect_Spring/resultInput">勤務実績入力</a><br>
・<a href="/Java_Architect_Spring/rest">休暇取得状況</a><br>
</div>
<div class="right-column">
<%-- <span style="border: 1px solid #000000">日付昇順</span> <br> --%>
<table border="1">
<tr>
<th>日付</th><th>氏名</th><th>シメイ</th><th>勤務状況</th>
<% 
for(WorkScheduleEntity ws : list){
	Date date = ws.getDate();
	String year = ysdf.format(date);
	String month = msdf.format(date);
	String day = dsdf.format(date);
	if(ws.getStatusName() != null){
%>
</tr>
<tr>
<td><%= year%>年<%= month%>月<%= day%>日</td><td><%= ws.getNameKanji()%></td><td><%= ws.getNameKana()%></td><td><%= ws.getStatusName()%></td>
</tr>
<% 
	}
}
%>
</table>
<a href="/Java_Architect_Spring/scheduleSearch">検索画面へ</a>
</div>
</div>
</body>
</html>