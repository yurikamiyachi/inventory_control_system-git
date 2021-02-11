<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <h2>【月別在庫数】</h2>
        <canvas id="myChart" height="450" width="1200"></canvas>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"
            type="text/javascript"></script>
        <script type="text/javascript">

   var barChartData = {
      labels : [${trade_name}],
      datasets : [
         {
            fillColor : "rgba(130,201,169,0.5)",
            strokeColor : "rgba(240,128,128,0.9)",
            highlightFill: "rgba(255,64,64,0.75)",
            highlightStroke: "rgba(255,64,64,1)",
            data : [${stock}]
         },

      ]
   }

   window.onload = function(){
      var ctx = document.getElementById("myChart").getContext("2d");
      window.myBar = new Chart(ctx).Bar(barChartData);
      responsive : true
   }
</script>

        <h4>${format2}の入出庫履歴一覧</h4>
        <table id="stock_list">
            <tbody>
                <tr>
                    <th class="trade_code">品番</th>
                    <th class="trade_name">品名</th>
                    <th class="receiving">入庫数</th>
                    <th class="shiping">出庫数</th>
                    <th class="stock">在庫数</th>
                </tr>
                <c:forEach var="inventory" items="${inventories}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="trade_code"><c:out value="${inventory.trade_code}" /></td>
                        <td class="trade_name"><c:out value="${inventory.trade_name}" /></td>
                        <td class="receiving"><c:out value="${inventory.receiving}" /></td>
                        <td class="shiping"><c:out value="${inventory.shiping}" /></td>
                        <td class="stock"><c:out value="${inventory.stock}" /></td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div id="pagination">
            （全 ${inventories_count} 件）<br />
            <c:forEach var="i" begin="1"
                end="${((inventories_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/aggregate/index?page=${i}' />"><c:out
                                value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <p>
                <a href="<c:url value='/inventories/index' />">《商品管理一覧に戻る》</a>
            </p>
            <p>
                <a href="<c:url value='/histories/index' />">《入出庫履歴一覧に戻る》</a>
            </p>
        </div>
    </c:param>
</c:import>