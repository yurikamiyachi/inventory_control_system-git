package controllers.histories;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.History;
import models.Inventory;
import models.validators.HistoryValidator;
import utils.DBUtil;

/**
 * Servlet implementation class HistoriesUpdateServlet
 */
@WebServlet("/histories/update")
public class HistoriesUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoriesUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            History h = em.find(History.class, (Integer)(request.getSession().getAttribute("history_id")));

            String receiving =request.getParameter("receiving");
            h.setReceiving(null);

            if(receiving != null && !receiving.equals("")) {
                receiving = request.getParameter("receiving");
                h.setReceiving(Integer.parseInt(request.getParameter("receiving")));
            }else{
                receiving=null;
            }

            String shiping =request.getParameter("shiping");
            h.setShiping(null);

            if(shiping != null && !shiping.equals("")) {
                shiping = request.getParameter("shiping");
                h.setShiping(Integer.parseInt(request.getParameter("shiping")));
            }else{
                shiping=null;
            }

            Inventory i = em.find(Inventory.class,(Integer)(request.getSession().getAttribute("inventory_id")));

            h.setInventory(i);
            List<String> errors = HistoryValidator.validate(h);
            if(errors.size()>0){
                em.close();

                request.setAttribute("_token",request.getSession().getId());
                request.setAttribute("history", h);
                request.setAttribute("inventory", i);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/histories/edit.jsp");
                rd.forward(request,response);

            }else{
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush", "更新が完了しました。");
                request.getSession().removeAttribute("history_id");
                response.sendRedirect(request.getContextPath() + "/histories/index");
            }
        }
    }
}