<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>【月別集計ページ】</h2>

        <c:if test="${errors != null}">
            <div id="flush_error">
                入力内容にエラーがあります。<br />
                <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" />
                    <br />
                </c:forEach>

            </div>
        </c:if>

        <form method="GET" action="<c:url value='/aggregate/index' />">
            <label for="history_date">年月</label><br /> <input type="month"
                name="history_month"
                value="<fmt:formatDate value='${history_date}' pattern='yyyy/MM' />" />
            <button type="submit">集計</button>

            <p>
                <a href="<c:url value='/inventories/index' />">《商品管理一覧に戻る》</a>
            </p>
            <p>
                <a href="<c:url value='/histories/index' />">《入出庫履歴一覧に戻る》</a>
            </p>
        </form>
    </c:param>
</c:import>