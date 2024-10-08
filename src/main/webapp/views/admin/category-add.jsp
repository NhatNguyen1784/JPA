<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 30/09/2024
  Time: 09:07 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Category-add</title>
</head>
  <body>
    <form action="<c:url value = "/admin/category/insert"/>" method="post" enctype="multipart/form-data">
      <!-- <c:if test = "${alterMsg != null}"> <h3 class="alter alter danger"> ${alterMsg}</h3> </c:if> -->

      <label for="categoryname">Category name:</label><br>
      <input type="text" id="categoryname" name="categoryname" value=""><br>

      <label for="image">Link Image:</label><br>
      <input type="text" id="image" name="image" value=""><br>
      <label for="image">Upload File:</label><br>
      <input type="file" id="image1" name="image1" value=""><br>

      <input type="radio" id="statuson" name="status" value="1">
      <label for="image">Hoạt động</label><br>
      <input type="radio" id="statusoff" name="status" value="0">
      <label for="image">không hoạt động</label><br>
      <hr/>
      <input type="submit" value="Add category"><br>
    </form>
  </body>
</html>
