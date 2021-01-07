<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.getSession().setAttribute("trade_code", "登録"); %>
<% String username = (String)request.getSession().getAttribute("trade_code"); %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>

<label for="trade_code">品番</label><br />
<input type="text" name="trade_code" value="${inventory.trade_code}" />
<br /><br />

<label for="trade_name">品名</label><br />
<input type="text" name="trade_name" value="${inventory.trade_name}" />
<br /><br />

<label for="receiving">入庫数</label><br />
<input type="text" name="receiving" value="${inventory.receiving}" />
<br /><br />

<label for="shiping">出庫数</label><br />
<input type="text" name="shiping" value="${inventory.shiping}" />
<br /><br />

<label for="stock">現在の在庫数</label><br />
<input type="text" name="stock" value="${inventory.stock}" />
<br /><br />

<label for="ordering_person">発注担当者</label><br />
<input type="text" name="ordering_person" value="${inventory.ordering_person}" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>