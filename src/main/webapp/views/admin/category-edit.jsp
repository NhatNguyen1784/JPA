<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 30/09/2024
  Time: 10:09 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
    <head>
        <title>Category-edit</title>
    </head>

    <body>
        <form action="<c:url value = "/admin/category/update"/>" method="post" enctype="multipart/form-data">
            <!-- <c:if test = "${alterMsg != null}"> <h3 class="alter alter danger"> ${alterMsg}</h3> </c:if> -->

            <input type="text" id="categoryid" name="categoryid" value="${cate.categoryid}" hidden="hidden"><br>
            <label for="categoryname">Category name:</label><br>
            <input type="text" id="categoryname" name="categoryname" value="${cate.categoryname}"><br>
            <label for="image">Link Image:</label><br>
            <input type="text" id="image" name="image" value="${cate.image}"><br>

            <c:if test="${cate.image.substring(0,5) == 'https'}">
                <c:url value="${cate.image}" var="imgUrl"></c:url>
            </c:if>

            <c:if test="${cate.image.substring(0,5) != 'https'}">
                <c:url value="/image?fname=${cate.image}" var="imgUrl"></c:url>
            </c:if>
            <img height="150" width="200" src="${imgUrl}"/>

            <br>

            <label for="image1">Upload File:</label><br>
            <input type="file" id="image1" name="image1"><br>

            <input type="radio" id="statuson" name="status" value="1" ${cate.status==1?'checked':''}>
            <label for="image">Hoạt động</label><br>
            <input type="radio" id="statusoff" name="status" value="0" ${cate.status==0?'checked':''}>
            <label for="image">khóa</label><br>
            <hr/>
            <input type="submit" value="Update"><br>
        </form>
    </body>
</html>
