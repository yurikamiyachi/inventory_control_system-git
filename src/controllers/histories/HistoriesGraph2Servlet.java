package controllers.histories;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.History;
import models.Inventory;
import utils.DBUtil;

/**
 * Servlet implementation class HistoriesIndexServlet
 */
@WebServlet("/histories/graph2")
public class HistoriesGraph2Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoriesGraph2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em =DBUtil.createEntityManager();

        Inventory i = em.find(Inventory.class, Integer.parseInt(request.getParameter("inventory_id")));

        String start_date = request.getParameter("history_month");
        String end_date = request.getParameter("history_month2");
        String str = start_date.replace("-", "/");
        String str2 = end_date.replace("-", "/");
        String add = str+"/01";
        String add2 = str2+"/01";

        int page = 1;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) { }

        List<Inventory> inventories = em.createNamedQuery("getAllInventories", Inventory.class)
                .getResultList();

        List<History> histories = em.createNamedQuery("getAllHistories", History.class)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();

        long histories_count = (long)em.createNamedQuery("getHistoriesCount", Long.class)
                .getSingleResult();

        Calendar c = Calendar.getInstance();
        Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            date=format.parse(add);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar c2 = Calendar.getInstance();
        Date date2=new Date();
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
        try {
            date2=format2.parse(add2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        c.setTime(date);
        c2.setTime(date2);

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;

        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH)+1;

        int lastday = c2.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstday=c.getActualMinimum(Calendar.DAY_OF_MONTH);
        LocalDate startDate = LocalDate.of(year,month,firstday);
        LocalDate endDate = LocalDate.of(year2,month2,lastday);

        Date sdate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date edate = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<History> histories3 = em.createNamedQuery("getMyHistories", History.class)
                .setParameter("inventory",i)
                .setParameter("startdate",sdate, TemporalType.DATE)
                .setParameter("enddate",edate,TemporalType.DATE)
                .getResultList();

        ArrayList<String> array = new ArrayList<String>();

        for(History monhis: histories3){
            Date tr=monhis.getHistory_date();
            SimpleDateFormat format3 = new SimpleDateFormat("yyyy/MM");
            String v=format3.format(tr);
            array.add(v);
        }

        List<String> month_histories2 = array.stream().distinct().collect(Collectors.toList());
        Collections.sort(month_histories2);

        String history_date = "";

        for(int v = 0; v < month_histories2.size();v++){
            if(v==month_histories2.size()-1){
                history_date =history_date+"'"+month_histories2.get(v)+"'";
            }else{
                history_date = history_date+"'"+month_histories2.get(v)+"'"+",";
            }
        }

        ArrayList<String> array2 = new ArrayList<String>();

        for(String monhis2: month_histories2){
            Calendar c3 = Calendar.getInstance();
            String add3 = monhis2+"/01";

            Date date3=new Date();
            SimpleDateFormat format3 = new SimpleDateFormat("yyyy/MM/dd");
            try {
                date3=format3.parse(add3);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            c3.setTime(date3);

            int year3 = c3.get(Calendar.YEAR);
            int month3 = c3.get(Calendar.MONTH)+1;

            int lastday3 = c3.getActualMaximum(Calendar.DAY_OF_MONTH);
            int firstday3=c3.getActualMinimum(Calendar.DAY_OF_MONTH);
            LocalDate startDate3 = LocalDate.of(year3,month3,firstday3);
            LocalDate endDate3 = LocalDate.of(year3,month3,lastday3);

            Date sdate3 = Date.from(startDate3.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date edate3 = Date.from(endDate3.atStartOfDay(ZoneId.systemDefault()).toInstant());

            List<History> histories2 = em.createNamedQuery("getMyHistories", History.class)
                    .setParameter("inventory",i)
                    .setParameter("startdate",sdate3, TemporalType.DATE)
                    .setParameter("enddate",edate3,TemporalType.DATE)
                    .getResultList();
            int shiping=0;
            for(History his2 :histories2){
                shiping =shiping+his2.getShiping();
            }
            array2.add(String.valueOf(shiping));
        }

        String trade_name=i.getTrade_name();

        em.close();

        request.setAttribute("histories", histories);
        request.setAttribute("histories_count", histories_count);
        request.setAttribute("inventories", inventories);
        request.setAttribute("history_date", history_date);
        request.setAttribute("array2", array2);
        request.setAttribute("trade_name",trade_name);
        request.setAttribute("page", page);

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/views/histories/graph.jsp");
        rd.forward(request,response);
    }
}
