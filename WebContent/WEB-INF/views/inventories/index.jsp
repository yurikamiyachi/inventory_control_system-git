<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>商品在庫　一覧</h2>
        <table id="inventory_list">
            <tbody>
                <tr>
                    <th>品番</th>
                    <th>品名</th>
                    <th>現在の在庫数</th>
                    <th>編集✍</th>
                </tr>
                <c:forEach var="inventory" items="${inventories}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${inventory.trade_code}" /></td>
                        <td><c:out value="${inventory.trade_name}" /></td>
                        <td><c:out value="${inventory.stock}" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${inventory.delete_flag == 1}">
                                    （削除済み）
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='/inventories/show?id=${inventory.id}' />">詳細を表示</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${inventories_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((inventories_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/inventories/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/inventories/new' />">新規商品の登録</a></p>
        <p><a href="<c:url value='/histories/index' />">入出庫管理</a></p>

    </c:param>
</c:import>