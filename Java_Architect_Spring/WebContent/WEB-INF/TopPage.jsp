<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  <link rel="stylesheet" href="/springArchitect/image/Login.css" type="text/css">-->
<link rel="stylesheet" href="/Java_Architect_Spring/image/Login.css" type="text/css">
<title>トップページ</title>
</head>
<body>
<h2>${staffEntity.nameKanji}のページ</h2>
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
<h3>メッセージ</h3>
<div style="padding: 10px; margin-bottom: 10px; border: 1px solid #333333;">
${restMsg} <br>
</div>
</div>
</div>
</body>
</html>