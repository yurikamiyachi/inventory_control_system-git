package controllers.aggregate;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.History;
import models.Inventory;
import utils.DBUtil;

/**
 * Servlet implementation class AggregateIndexServlet
 */
@WebServlet("/aggregate/index")
public class AggregateIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int page = 0;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggregateIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param history_month
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Calendar c = Calendar.getInstance();
        String tr = request.getParameter("history_month");
        String add = tr+"/01";
        String str = add.replace("-", "/");

        Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            date=format.parse(str);
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

        List<Inventory> inventories = em.createNamedQuery("getAllInventories", Inventory.class)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();

        long inventories_count = (long)em.createNamedQuery("getInventoriesCount", Long.class)
                .getSingleResult();

        for(Inventory inv : inventories){

            List<History> histories = em.createNamedQuery("getMyHistories2", History.class)
                    .setParameter("startdate",sdate, TemporalType.DATE)
                    .setParameter("enddate",edate,TemporalType.DATE)
                    .getResultList();

            int receiving = 0;
            int shiping = 0;
            int stock = 0;

            for(History his: histories){

                if(inv.getId().equals(his.getInventory().getId())){
                    receiving += his.getReceiving();
                    shiping += his.getShiping();
                }
                inv.setReceiving(receiving);
                inv.setShiping(shiping);
                stock=receiving-shiping;
                inv.setStock(stock);
            }
        }

        ArrayList<String> array = new ArrayList<String>();
        for(Inventory inv :inventories){
            array.add(inv.getTrade_name());
        }
        String trade_name = "";
        for(int v = 0; v < inventories.size();v++){
            if(v==inventories.size()-1){
                trade_name =trade_name+"'"+array.get(v)+"'";
            }else{
                trade_name = trade_name+"'"+array.get(v)+"'"+",";
            }
        }

        ArrayList<String> array2 = new ArrayList<String>();
        for(Inventory inv :inventories){
            array2.add(String.valueOf(inv.getStock()));
        }
        String stock  = "";
        for(int v = 0; v < inventories.size();v++){
            if(v==inventories.size()-1){
                stock =stock+"'"+array2.get(v)+"'";
            }else{
                stock = stock+"'"+array2.get(v)+"'"+",";
            }
        }

        String format2 = new SimpleDateFormat("yyyy年MM月").format(date);

        em.close();

        request.setAttribute("inventories", inventories);
        request.setAttribute("inventories_count", inventories_count);
        request.setAttribute("trade_name", trade_name);
        request.setAttribute("stock", stock);
        HttpSession session = request.getSession();
        session.setAttribute("format2", format2);

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/aggregate/index.jsp");
        rd.forward(request, response);
    }
}
