package controllers.histories;

import java.io.IOException;
import java.sql.Date;

import javax.persistence.EntityManager;
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
 * Servlet implementation class HistoriesNewServlet
 */
@WebServlet("/histories/new")
public class HistoriesNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoriesNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token",request.getSession().getId());

        EntityManager em = DBUtil.createEntityManager();

        Inventory i = em.find(Inventory.class,Integer.parseInt(request.getParameter("id")));
        em.close();

        request.setAttribute("_token", request.getSession().getId());

        History h = new History();
        h.setInventory(i);
        h.setHistory_date(new Date(System.currentTimeMillis()));
        request.setAttribute("history", h);

        RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/views/histories/new.jsp");
        rd.forward(request, response);
    }
}
