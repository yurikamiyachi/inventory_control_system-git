<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${inventory != null}">
                <h2>id : ${inventory.id} の商品情報　編集ページ</h2>
                <p>(パスワードは変更する場合のみ入力してください。)</p>
                <form method="POST" action="<c:url value='/inventories/update' />">
                    <c:import url="_form.jsp" />
                </form>

                <p><a href="#" onclick="confirmDestroy();">この商品情報を削除する</a></p>
                <form method="POST" action="<c:url value='/inventories/destroy' />">
                    <input type="hidden" name="_token" value="${_token}" />
                </form>
                <script>
                function confirmDestroy(){
                    if(confirm("本当に削除してよろしいですか？")){
                        document.form[1].submit();
                    }
                }
                </script>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/inventories/index' />"></a></p>
    </c:param>
</c:import>