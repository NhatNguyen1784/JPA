<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 09/10/2024
  Time: 11:47 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Video-edit</title>
</head>
<body>

<form action="<c:url value = "/admin/video/update"/>" method="post" enctype="multipart/form-data">
  <!-- <c:if test = "${alterMsg != null}"> <h3 class="alter alter danger"> ${alterMsg}</h3> </c:if> -->

  <label for="videoId">Video id:</label><br>
  <input type="text" id="videoId" name="videoId" value="${vid.videoId}"><br>


  <label for="title">Title:</label><br>
  <input type="text" id="title" name="title" value="${vid.title}"><br>

  <label for="posterold">Poster:</label><br>
  <input type="text" id="posterold" name="posterold" value="${vid.poster}"><br>
  <label for="posternew">Upload new poster:</label><br>
  <input type="file" id="posternew" name="posternew" value=""><br>

  <label for="view">Category:</label><br>

  <select name="categories" id="categories">

    <c:forEach items="${categoryList}" var="cate">
      <!-- So sánh cate.categoryid với cateSelected và gán selected nếu trùng khớp -->
      <option value="${cate.categoryid}" ${cate.categoryid == cateSelected ? 'selected="selected"' : ''}>
          ${cate.categoryname}
      </option>
    </c:forEach>



  </select>
  <br>
  <label for="description">Description:</label><br>
  <input type="text" id="description" name="description" value="${vid.description}"><br>

  <input type="radio" id="activeon" name="active" value="1" ${vid.active == 1 ? 'checked': '' }>
  <label for="activeon">Hoạt động</label><br>
  <input type="radio" id="activeoff" name="active" value="0" ${vid.active == 0 ? 'checked' : ''}>
  <label for="activeoff">không hoạt động</label><br>

  <label for="view">View:</label><br>
  <input type="number" id="view" name="view" value="${vid.views}"><br>

  <hr/>
  <input type="submit" value="Update video"><br>
</form>
</body>
</html>
