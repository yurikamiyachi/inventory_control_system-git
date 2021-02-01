<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" />
            <br />
        </c:forEach>

    </div>
</c:if>

<label for="history_date">登録日</label>
<br />
<input type="date" name="history_date"
    value="<fmt:formatDate value='${history.history_date}' pattern='yyyy-MM-dd' />" />
<br />
<br />

<label for="trade_code">品番</label>
<br />
<c:out value="${inventory.trade_code}" />
<br />
<br />

<label for="trade_name">品名</label>
<br />
<c:out value="${inventory.trade_name}" />
<br />
<br />

<label for="receiving">入庫数</label>
<br />
<input type="text" name="receiving" value="${history.receiving}" />
<br />
<br />

<label for="shiping">出庫数</label>
<br />
<input type="text" name="shiping" value="${history.shiping}" />
<br />
<br />

<label for="history_name">登録者</label>
<br />
<c:out value="${sessionScope.login_employee.name}" />
<br />
<br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>