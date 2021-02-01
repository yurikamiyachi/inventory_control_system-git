<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${history != null}">
                <h2>入出庫管理編集ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>登録日</th>
                            <td><fmt:formatDate value="${history.history_date}"
                                    pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>品番</th>
                            <td><c:out value="${inventory.trade_code}" /></td>
                        </tr>
                        <tr>
                            <th>品名</th>
                            <td><c:out value="${inventory.trade_name}" /></td>
                        </tr>
                        <tr>
                            <th>入庫数</th>
                            <td><pre>
                                    <c:out value="${history.receiving}" />
                                </pre></td>
                        </tr>
                        <tr>
                            <th>出庫数</th>
                            <td><pre>
                                    <c:out value="${history.shiping}" />
                                </pre></td>
                        </tr>
                    </tbody>
                </table>

                <p>
                    <a href="<c:url value="/histories/edit?id=${histories.id}" />">入出庫数を編集する</a>
                </p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p>
            <a href="<c:url value="/histories/index" />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>