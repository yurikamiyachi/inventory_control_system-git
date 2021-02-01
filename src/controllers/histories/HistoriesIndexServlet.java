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
@WebServlet("/histories/index")
public class HistoriesIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoriesIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em =DBUtil.createEntityManager();

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

        List<History> month_histories = em.createNamedQuery("getAllHistories", History.class)
                .getResultList();

        ArrayList<String> array = new ArrayList<String>();

        for(History monhis: month_histories){
            Date tr2=monhis.getHistory_date();
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM");
            String str2=format2.format(tr2);
            array.add(str2);
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
            Calendar c = Calendar.getInstance();
            String add = monhis2+"/01";

            Date date=new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            try {
                date=format.parse(add);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            c.setTime(date);

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH)+1;

            int lastday = c.getActualMaximum(Calendar.DAY_OF_MONTH);
            int firstday=c.getActualMinimum(Calendar.DAY_OF_MONTH);
            LocalDate startDate = LocalDate.of(year,month,firstday);
            LocalDate endDate = LocalDate.of(year,month,lastday);

            Date sdate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date edate = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            Inventory i = em.find(Inventory.class, 1);
            List<History> histories2 = em.createNamedQuery("getMyHistories", History.class)
                    .setParameter("inventory",i)
                    .setParameter("startdate",sdate, TemporalType.DATE)
                    .setParameter("enddate",edate,TemporalType.DATE)
                    .getResultList();
            int shiping=0;
            for(History his2 :histories2){
                shiping =shiping+his2.getShiping();
            }
            array2.add(String.valueOf(shiping));

            System.out.println(array2);
        }

        em.close();

        request.setAttribute("histories", histories);
        request.setAttribute("histories_count", histories_count);
        request.setAttribute("inventories", inventories);
        request.setAttribute("history_date", history_date);
        request.setAttribute("page", page);

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/views/histories/index.jsp");
        rd.forward(request,response);
    }
}
