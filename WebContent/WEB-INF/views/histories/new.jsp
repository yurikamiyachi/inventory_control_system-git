<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
    <h2>【入出庫数 新規登録ページ】</h2>

        <form method="POST" action="<c:url value='/histories/create?id=${history.inventory.id}' />">
            <c:import url="_form.jsp" />
        </form>
        <p>
            <a href="<c:url value='/inventories/index' />">《商品管理一覧に戻る》</a>
        </p>
        <p>
            <a href="<c:url value='/histories/index' />">《入出庫履歴一覧》</a>
        </p>
    </c:param>
</c:import>