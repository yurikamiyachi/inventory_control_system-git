<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${history != null}">
                <h2>【${history.inventory.trade_name}の編集ページ】</h2>
                <form method="POST" action="<c:url value='/histories/update' />">
                    <c:import url="_form.jsp" />
                </form>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p>
            <a href="<c:url value='/histories/index' />">《入出庫履歴一覧に戻る》</a>
        </p>
        <p>
            <a href="#" onclick="confirmDestroy();">《削除する》</a>
        </p>
        <form method="POST"
            action="${pageContext.request.contextPath}/histories/destroy">
            <input type="hidden" name="_token" value="${_token}" />
        </form>
        <script>
        function confirmDestroy() {
            if(confirm("本当に削除してよろしいですか？")) {
                document.forms[1].submit();
            }
        }
        </script>
    </c:param>
</c:import>