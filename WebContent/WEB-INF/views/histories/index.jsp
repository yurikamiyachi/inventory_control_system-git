<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>在庫　一覧</h2>
            <table id="history_list">
                <tbody>
                    <tr>
                        <th class="trade_code">品番</th>
                        <th class="trade_name">品名</th>
                        <th class="receiving">入庫数</th>
                        <th class="shiping">出庫数</th>
                        <th class="history_date">登録日</th>
                        <th class="trade_action">操作</th>
                    </tr>
                    <c:forEach var="history" items="${histories}" varStatus="status">
                        <tr class="row${status.count % 2}">
                            <td class="trade_code"><c:out value="${history.trade_code}" /></td>
                            <td class="trade_name"><c:out value="${history.trade_name}" /></td>
                            <td class="receiving"><c:out value="${history.receiving}" /></td>
                            <td class="shiping"><c:out value="${history.shiping}" /></td>
                            <td class="history_date"><fmt:formatDate value='${history.history_date}' pattern='yyyy-MM-dd' /></td>
                            <td class="trade_action"><a href="<c:url value='/histories/show?id=${history.id}' />">詳細を見る</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div id="pagination">
            （全 ${histories_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((histories_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/histories/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/histories/new' />">新規品番の登録</a></p>

    </c:param>
</c:import>