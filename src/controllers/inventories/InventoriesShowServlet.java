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
 * Servlet implementation class InventoriesShowServlet
 */
@WebServlet("/inventories/show")
public class InventoriesShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InventoriesShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Inventory i = em.find(Inventory.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("inventory",i);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/inventories/show.jsp");
        rd.forward(request,response);
    }
}