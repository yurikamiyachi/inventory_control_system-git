package controllers.inventories;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Inventory;
import utils.DBUtil;

/**
 * Servlet implementation class InventoriesEditServlet
 */
@WebServlet(name = "inventories/edit", urlPatterns = { "/inventories/edit" })
public class InventoriesEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InventoriesEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Inventory e = em.find(Inventory.class,Integer.parseInt(request.getParameter("id")));
        em.close();

        request.setAttribute("inventory", e);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("inventory_id",e.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/inventories/edit.jsp");
        rd.forward(request,response);
    }

}
