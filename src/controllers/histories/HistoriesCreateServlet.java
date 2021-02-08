package controllers.histories;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.History;
import models.Inventory;
import models.validators.HistoryValidator;
import utils.DBUtil;

/**
 * Servlet implementation class HistoriesCreateServlet
 */
@WebServlet("/histories/create")
public class HistoriesCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoriesCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token=(String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Inventory i = em.find(Inventory.class,Integer.parseInt(request.getParameter("id")));

            History h = new History();
            h.setEmployee((Employee)request.getSession().getAttribute("login_employee"));


            Date history_date = new Date(System.currentTimeMillis());
            String rd_str = request.getParameter("history_date");
            if(rd_str != null && !rd_str.equals("")) {
                history_date = Date.valueOf(request.getParameter("history_date"));
            }

            String receiving = request.getParameter("receiving");
            if(receiving != null && !receiving.equals("")) {
                h.setReceiving(Integer.parseInt(request.getParameter("receiving")));
            }else{
                receiving=null;
            }

            String shiping = request.getParameter("shiping");
            if(shiping != null && !shiping.equals("")) {
                shiping = request.getParameter("shiping");
                h.setShiping(Integer.parseInt(shiping));
            }else{
                shiping=null;
            }

            h.setInventory(i);
            h.setHistory_date(history_date);

            List<String> errors = HistoryValidator.validate(h);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("history", h);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/histories/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(h);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/histories/index");
            }
        }
    }
}
