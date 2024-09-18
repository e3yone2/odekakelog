<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.LogDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>おでかけログ</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>おでかけログ</h1>

<div class="fuwafuwa">
<img src="img/cloud1.svg" alt="雲の画像" title="雲"></div>
<div class="fuwafuwa">
<img src="img/cloud2.svg" alt="雲の画像" title="雲"></div>
<div class="fuwafuwa">
<img src="img/bird1.svg" alt="鳥の画像" title="雲"></div>
<div class="fuwafuwa">
<img src="img/cloud4.svg" alt="雲の画像" title="雲"></div>
<div class="fuwafuwa2">
<img src="img/bird1.svg" alt="雲の画像" title="雲"></div>

<div class ="welcome">
<p>ようこそ<c:out value="${name}"/>さん</p>
</div>

<p id="logout"><a href ="LogoutServlet">ログアウト</a></p>
<div class ="locationbutton">
<button id="find-me">現在地を取得</button><br />
<p id="status"></p>
<a id="map-link" target="_blank"></a>
<script type="text/javascript" src="lib/location.js"></script>

<form id="myForm"  action="LocationServlet" method="post">
	<input type="hidden" id="ido" name="ido" value="">
	<input type="hidden" id="keido" name="keido" value="">
	<input type="submit" value="取得した現在地を投稿">
	
	<script>
	function geoFindMe() {
		  const status = document.querySelector("#status");
		  const mapLink = document.querySelector("#map-link");

		  mapLink.href = "";
		  mapLink.textContent = "";

		  function success(position) {
		    const latitude = position.coords.latitude;
		    const longitude = position.coords.longitude;

		    status.textContent = "";
		    mapLink.href = `https://www.openstreetmap.org/#map=18/${latitude}/${longitude}`;
		    mapLink.textContent = `緯度: ${latitude}°、経度: ${longitude}°`;

			document.getElementById("ido").value = latitude;
			document.getElementById("keido").value = longitude;
		  }

		  function error() {
		    status.textContent = "Unable to retrieve your location";
		  }

		  if (!navigator.geolocation) {
		    status.textContent = "このブラウザーは位置情報に対応していません";
		  } else {
		    status.textContent = "位置情報を取得中…";
		    navigator.geolocation.getCurrentPosition(success, error);
		  }
		}

		document.querySelector("#find-me").addEventListener("click", geoFindMe);	
	</script>
</form>
</div>

<c:if test="${not empty errorMsg }">
<p class= "errorMsg-l" style ="color:red"><c:out value="${errorMsg}"></c:out></p>
</c:if>
<c:if test="${not empty Msg }">
<p class= "Msg-l" style ="color:red"><c:out value="${Msg}"></c:out></p>
</c:if>

<%
List <LogDTO> logDTOList = (List)session.getAttribute("logDTOList");
int listSize = logDTOList.size();
int count = 0;
%>

<div class="box-logs">
    <div class="box-title">おでかけ記録</div>
 
  	<c:forEach var="logDTO" items="${logDTOList}">
 			
 		<table class="inside-table">
 		<tr>
 			
 			<td><%= listSize - count %>個目の記録</td>
 			  	<% count++; %>
 		</tr>
		<tr>
			<td>いつ：<c:out value ="${logDTO.date}"/> 
				
			</td>
		</tr>
		<tr>
			<td>場所：<a href="<c:out value ="${logDTO.location}"/>">地図を見る</a>
				
			</td>
		</tr> 
		<tr>
			<td>テキスト：<c:out value ="${logDTO.text}"/>
				
			</td>
			
		</tr>
		<tr>
			<td>画像:<c:out value ="${logDTO.img}"/></td>
			
		</tr>
		<tr><td><hr></td></tr>
		<tr>
		
			<td><form id="dateForm" action="EditDateServlet" method="post">
				 <input type="hidden" name=e_log_id value= <c:out value ="${logDTO.log_id}"/>>
				<input type="date" name="date" class="js-datepicker2" size="11" placeholder="yyyy-mm-dd" /> 
				<input type="submit" value ="日付を選択して変更" class="dateform"></form>
			</td>
		</tr>	
		
		<tr>
			<td><form id="locationForm"  action="EditLocationServlet" method="post">
				<input type="hidden" name=e_log_id value= <c:out value ="${logDTO.log_id}"/>>
				<input type="text" id="location" name="location" placeholder="地図urlを入力">
				<input type = "submit" value = "場所を編集" class="location-button"> </form>
			</td>
		</tr>
		
		<tr>
			<td><form id="textForm"  action="EditTextServlet" method="post">
				<input type="hidden" name=e_log_id value= <c:out value ="${logDTO.log_id}"/>>
				<input type="text" id="text" name="text">
				<input type = "submit" value = "テキストを編集" class ="text-button"> </form>
			</td>
		</tr>		
		
		<tr>
			<td><form id="imgForm"  action="EditimgServlet" method="post">
				<input type="hidden" name=e_log_id value= <c:out value ="${logDTO.log_id}"/>>
				<input type="img" id="img" name="img">
				<input type = "submit" value = "画像を編集" class="img-button"> </form>
				
				<form id="deleteForm"  action="DeleteLogServlet" method="post">
				<input type="hidden" name=e_log_id value= <c:out value ="${logDTO.log_id}"/>>
				<input type = "submit" value = "この記録を削除" class = "delete-button"> </form>
				
			</td>
		</tr>	
		</table>
	</c:forEach>

</div>



</body>
</html>
