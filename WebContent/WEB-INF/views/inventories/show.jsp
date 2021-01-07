<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${inventory != null}">
                <h2>${inventory.trade_name} の詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>品番</th>
                            <td><c:out value="${inventory.trade_code}" /></td>
                        </tr>
                        <tr>
                            <th>品名</th>
                            <td><c:out value="${inventory.trade_name}" /></td>
                        </tr>
                        <tr>
                            <th>発注担当者</th>
                            <td><c:out value="${inventory.ordering_person}" /></td>
                         </tr>
                        <tr>
                            <th>登録者</th>
                            <td><c:out value="${sessionScope.login_employee.name}" /></td>
                         </tr>
                         <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${inventory.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                         </tr>
                         <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${inventory.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                         </tr>
                    </tbody>
                </table>

                <p><a href="<c:url value='/inventories/edit?id=${inventory.id}' />">この商品情報を編集する</a></p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>
        <p><a href="<c:url value='/inventories/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>