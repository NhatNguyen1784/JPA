<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 30/09/2024
  Time: 08:22 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<a href="<c:url value = "/admin/category/add"/>">Add category</a>
<br>
<a href="<c:url value = "/admin/videos"/>">Video list</a>
<table>
    <tr>
        <th>STT</th>
        <th>image</th>
        <th>Category name</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${listcate}" var="cate" varStatus="STT">
        <tr class="odd gradeX">
            <td>${STT.index+1 }</td>
            <c:if test="${cate.image.substring(0,5) == 'https'}">
                <c:url value="${cate.image}" var="imgUrl"></c:url>
            </c:if>
            <c:if test="${cate.image.substring(0,5) != 'https'}">
                <c:url value="/image?fname=${cate.image}" var="imgUrl"></c:url>
            </c:if>
            <td><img height="150" width="200" src="${imgUrl}"/></td>
            <td>${cate.categoryname}</td>

            <c:if test="${cate.status == 1}"> <td>Hoạt động</td> </c:if>
            <c:if test="${cate.status != 1}"> <td>Không hoạt động</td> </c:if>
            <td>
                <a href="<c:url value='/admin/category/edit?id=${cate.categoryid}'/>"
                   class="center">Sửa</a>

                <a href="<c:url value='/admin/category/delete?id=${cate.categoryid}'/>"
                   class="center">Xóa</a>
            </td>
        </tr>
    </c:forEach>

</table>
