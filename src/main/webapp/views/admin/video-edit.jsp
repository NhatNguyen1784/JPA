<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 09/10/2024
  Time: 11:47 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Video-edit</title>
</head>
<body>
<form action="<c:url value = "/admin/video/insert"/>" method="post" enctype="multipart/form-data">
  <!-- <c:if test = "${alterMsg != null}"> <h3 class="alter alter danger"> ${alterMsg}</h3> </c:if> -->

  <label for="videoId">Video id:</label><br>
  <input type="text" id="videoId" name="videoId" value=""><br>

  <label for="title">Title:</label><br>
  <input type="text" id="title" name="title" value=""><br>

  <label for="poster">Upload Poster:</label><br>
  <input type="file" id="poster" name="poster" value=""><br>

  <label for="view">Category:</label><br>
  <select name="categories" id="categories">
    <c:forEach items="${categoryList}" var="cate">
      <option value="${cate.categoryid}">${cate.categoryname}</option>
    </c:forEach>

  </select>

  <label for="description">Description:</label><br>
  <input type="text" id="description" name="description" value=""><br>

  <input type="radio" id="activeon" name="active" value="1">
  <label for="poster">Hoạt động</label><br>
  <input type="radio" id="activeoff" name="active" value="0">
  <label for="poster">không hoạt động</label><br>

  <label for="view">View:</label><br>
  <input type="number" id="view" name="view" value="0"><br>

  <hr/>
  <input type="submit" value="Add video"><br>
</form>
</body>
</html>
