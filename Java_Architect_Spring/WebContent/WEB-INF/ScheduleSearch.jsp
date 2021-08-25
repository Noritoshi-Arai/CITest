<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/Java_Architect_Spring/image/Login.css" type="text/css">
<title>出勤予定検索</title>
</head>
<body>
<h2>出勤予定検索</h2>
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
<form action="/Java_Architect_Spring/scheduleResult" method="post">
氏名：<input type="text" name="nameKanji"> <br>
シメイ：<input type="text" name="nameKana"> <br>
日付：<input type="text" name="startYear" maxlength="4">年<input type="text" name="startMonth" maxlength="2">月<input type="text" name="startDay" maxlength="2">日～ <br>
<input type="text" name="finishYear" maxlength="4">年<input type="text" name="finishMonth" maxlength="2">月<input type="text" name="finishDay" maxlength="2">日 <br>
<input type="submit" value="検索">
</form>
</div>
</div>
</body>
</html>