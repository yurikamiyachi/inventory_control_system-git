<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品管理システム</title>
<link rel="stylesheet" href="<c:url value='/css/reset.css' />">
<link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h1>商品管理システム</h1>
            <c:if test="${sessionScope.login_employee != null}">
                <c:if test="${sessionScope.login_employee.admin_flag == 1}">
                    <a href="<c:url value='/employees/index' />">従業員管理</a>&nbsp;
                        </c:if>
                <a href="<c:url value='/inventories/index' />">商品管理</a>&nbsp;
                <a href="<c:url value='/histories/index' />">入出庫管理</a>&nbsp;
                    </c:if>
        </div>
        <c:if test="${sessionScope.login_employee != null}">
            <div id="employee_name">
                <c:out value="${sessionScope.login_employee.name}" />
                &nbsp;さん&nbsp;&nbsp;&nbsp; <a href="<c:url value='/logout' />">ログアウト</a>
            </div>
        </c:if>
    </div>
    <div id="content">${param.content}</div>
    <div id="footer">by Sample Corporation.</div>
</body>
</html>