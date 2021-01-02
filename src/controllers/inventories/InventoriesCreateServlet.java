package controllers.inventories;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Inventory;
import models.validators.InventoryValidator;
import utils.DBUtil;

/**
 * Servlet implementation class InventoriesCreateServlet
 */
@WebServlet("/inventories/create")
public class InventoriesCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InventoriesCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Inventory e = new Inventory();

            e.setTrade_code(request.getParameter("trade_code"));
            e.setTrade_name(request.getParameter("trade_name"));
            e.setOrder_flag(Integer.parseInt(request.getParameter("order_flag")));
            e.setReceiving(Integer.parseInt(request.getParameter("receiving")));
            e.setShiping(Integer.parseInt(request.getParameter("shiping")));
            e.setStock(Integer.parseInt(request.getParameter("stock")));
            e.setHistory(Integer.parseInt(request.getParameter("history")));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            e.setCreated_at(currentTime);
            e.setUpdated_at(currentTime);
            e.setDelete_flag(0);

            List<String> errors = InventoryValidator.validate(e, true);
            if(errors.size()>0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("inventory", e);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/inventories/new.jsp");
                rd.forward(request,response);
            }else{
                em.getTransaction().begin();
                em.persist(e);
                em.getTransaction().commit();
                request.getSession().setAttribute("flush","登録が完了しました。");
                em.close();

                response.sendRedirect(request.getContextPath()+"/inventories/index");
            }
        }
    }

}
