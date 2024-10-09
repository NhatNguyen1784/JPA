<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 09/10/2024
  Time: 07:19 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>

<html>
<head>
    <title>Video-list</title>
</head>
<body>
<a href="<c:url value = "/admin/video/add"/>">Add video</a>
<table>
    <tr>
        <th>STT</th>
        <th>Title</th>
        <th>Poster</th>
        <th>Description</th>
        <th>Views</th>
        <th>Category</th>
        <th>Active</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${videoList}" var="vid" varStatus="STT">
        <tr class="odd gradeX">
            <td>${STT.index+1 }</td>

            <td>${vid.title}</td>          <%-- title --%>

            <c:if test="${vid.poster.substring(0,5) == 'https'}">
                <c:url value="${vid.poster}" var="imgUrl"></c:url>
            </c:if>
            <c:if test="${vid.poster.substring(0,5) != 'https'}">
                <c:url value="/image?fname=${vid.poster}" var="imgUrl"></c:url>
            </c:if>
            <td><img height="150" width="200" src="${imgUrl}"/></td> <%-- poster --%>

            <td>${vid.description}</td>   <%-- description --%>

            <td>${vid.views}</td>     <%-- views --%>

            <td>${vid.category.categoryname}</td>     <%-- category --%>

            <c:if test="${vid.active == 1}">    <%-- active --%>
                <td>Hoạt động</td>
            </c:if>
            <c:if test="${vid.active != 1}">
                <td>Không hoạt động</td>
            </c:if>
            <td>
                <a href="<c:url value='/admin/video/edit?videoId=${vid.videoId}'/>"
                   class="center">Sửa</a>

                <a href="<c:url value='/admin/video/delete?videoId=${vid.videoId}'/>"
                   class="center">Xóa</a>
            </td>

        </tr>
    </c:forEach>

</table>
</body>
</html>
