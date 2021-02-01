<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <h2>【入出庫履歴 一覧】</h2>
        <table id="history_list">
            <tbody>
                <tr>
                    <th class="history_date">登録日</th>
                    <th class="trade_code">品番</th>
                    <th class="trade_name">品名</th>
                    <th class="receiving">入庫数</th>
                    <th class="shiping">出庫数</th>
                    <th class="history_name">登録者</th>
                    <th class="edit">編集</th>
                </tr>
                <c:forEach var="history" items="${histories}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="history_date"><fmt:formatDate
                                value='${history.history_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="trade_code"><c:out
                                value="${history.inventory.trade_code}" /></td>
                        <td class="trade_name"><a
                            href="<c:url value='/histories/graph?id=${history.inventory.id}' />">${history.inventory.trade_name}</a></td>
                        <td class="receiving"><c:out value="${history.receiving}" /></td>
                        <td class="shiping"><c:out value="${history.shiping}" /></td>
                        <td class="history_name"><c:out
                                value="${history.employee.name}" /></td>
                        <td class="edit"><a
                            href="<c:url value='/histories/edit?id=${history.id}' />">編集する</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${histories_count} 件）<br />
            <c:forEach var="i" begin="1"
                end="${((histories_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/histories/index?page=${i}' />"><c:out
                                value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p>
            <a href="<c:url value='/inventories/index' />">《商品管理一覧に戻る》</a>
        </p>
        <p>
            <a href="<c:url value='/aggregate/new' />">《月別小計》</a>
        </p>
    </c:param>
</c:import>