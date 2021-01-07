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
 * Servlet implementation class InventoriesUpdateServlet
 */
@WebServlet("/inventories/update")
public class InventoriesUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InventoriesUpdateServlet() {
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

            Inventory e = em.find(Inventory.class, (Integer)(request.getSession().getAttribute("inventory_id")));

            Boolean trade_codeDupricateCheckFlag=true;
            if(e.getTrade_code().equals(request.getParameter("trade_code"))){
                trade_codeDupricateCheckFlag=false;
            }else{
                e.setTrade_code(request.getParameter("trade_code"));
            }

            e.setTrade_name(request.getParameter("trade_name"));
            e.setOrdering_person(request.getParameter("ordering_person"));
            e.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            e.setDelete_flag(0);

            List<String> errors=InventoryValidator.validate(e, trade_codeDupricateCheckFlag);
            if(errors.size()>0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("inventory",e);
                request.setAttribute("errors", errors);

                RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/inventories/edit.jsp");
                rd.forward(request, response);
            }else{
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush","更新が完了しました。");
                request.getSession().removeAttribute("inventory_id");

                response.sendRedirect(request.getContextPath()+"/inventories/index");
            }
        }
    }

}
