<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>商品管理システム</title>
<link rel="stylesheet" href="<c:url value='/css/reset.css' />">
<link href="https://fonts.googleapis.com/css?family=Sawarabi+Gothic"
    rel="stylesheet">
<link rel="stylesheet" href="<c:url value='/css/style.css' />">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
$(function(){
    $('#header_menu li a').each(function(){
        var $href = $(this).attr('href');
        if(location.href.match($href)) {
        $(this).addClass('active');
        } else {
        $(this).removeClass('active');
        }
    });
});
</script>
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h1>商品管理システム</h1>
            <p class="login">
                <c:if test="${sessionScope.login_employee != null}">
                    <c:out value="${sessionScope.login_employee.name}" />
                &nbsp;さん&nbsp;&nbsp;&nbsp; <a
                        href="<c:url value='/logout' />">ログアウト</a>
                </c:if>
            </p>
            <ul id="header_menu">
                <li class="header_menu active"><a
                    href="<c:url value='/inventories/index' />">《商品管理》</a></li>
                <li class="header_menu"><a
                    href="<c:url value='/stocks/index' />">《在庫管理》</a></li>
                <li class="header_menu"><a
                    href="<c:url value='/aggregate/new' />">《月別小計》</a></li>
                <c:if test="${sessionScope.login_employee != null}">
                    <c:if test="${sessionScope.login_employee.admin_flag == 1}">
                        <li class="header_menu"><a
                            href="<c:url value='/employees/index' />">《従業員管理》</a></li>
                    </c:if>
                </c:if>
            </ul>
        </div>
    </div>
    <div id="content">${param.content}</div>
    <div id="footer">by Yurika Miyachi</div>
</body>
</html>