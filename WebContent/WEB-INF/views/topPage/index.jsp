<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>商品 一覧</h2>

        <form method="POST" action="<c:url value='/inventories/index' />">
        </form>
    </c:param>
</c:import>