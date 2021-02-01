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
        <h2>【在庫数推移】</h2>
        <script
            src="https://cdn.jsdelivr.net/npm/chart.js@2.9.3/dist/Chart.min.js"></script>
        <canvas id="myChart"></canvas>

        <script>
   var myData = document.getElementById('myChart');
   var chart = new Chart(myData, {
       type: 'line',
       data: {
           labels: [${history_date}],
           datasets: [ {
               label: '${trade_name}',
               backgroundColor: 'rgb(75, 192, 192)',
               borderColor: 'rgb(75, 192, 192)',
               data: ${array2},
               lineTension: 0,
               fill: false
           }]
       },
       options: {
           scales: {
               xAxes: [{
                   scaleLabel: {
                       display: true,
                       labelString: '年月'
                   }
               }],
               yAxes: [{
                   scaleLabel: {
                       display: true,
                       labelString: '在庫数'
                   }
               }]
           }
       }
   });
</script>
        <br />
        <form method="GET" action="<c:url value='/histories/graph2' />">
            <label for="history_date">【期間指定】</label><br /> <input type="month"
                name="history_month"
                value="<fmt:formatDate value='${start_date}' pattern='yyyy/MM' />" />
            ～ <label for="history_date"></label><input type="month"
                name="history_month2"
                value="<fmt:formatDate value='${end_date}' pattern='yyyy/MM' />" />
            <input type="hidden" name="inventory_id" value='${inventory_id}' />
            <button type="submit">表示</button>
            <br />

            <p>
                <a href="<c:url value='/histories/index' />">《入出庫履歴一覧に戻る》</a>
            </p>
            <p>
                <a href="<c:url value='/inventories/index' />">《商品管理一覧に戻る》</a>
            </p>
            </div>
    </c:param>
</c:import>