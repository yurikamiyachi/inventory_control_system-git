<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");
  String name = request.getParameter("history_month");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>【入出庫履歴一覧】</h2>
        <table id="stock_list">
            <tbody>
                <tr>
                    <th class="history_date">登録日</th>
                    <th class="receiving">入庫数</th>
                    <th class="shiping">出庫数</th>
                </tr>
                <c:forEach var="history" items="${histories}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="history_date"><c:out
                                value="${history.history_date}" /></td>
                        <td class="receiving"><c:out value="${history.receiving}" /></td>
                        <td class="shiping"><c:out value="${history.shiping}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p>
            <a href="<c:url value='/stocks/index' />">《在庫管理一覧に戻る》</a>
        </p>
    </c:param>
</c:import>