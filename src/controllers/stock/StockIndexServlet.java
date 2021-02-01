package controllers.stock;

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
import utils.DBUtil;

/**
 * Servlet implementation class StockIndexServlet
 */
@WebServlet("/stocks/index")
public class StockIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }

        List<Inventory> inventories = em.createNamedQuery("getAllInventories", Inventory.class)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();

        long inventories_count = (long)em.createNamedQuery("getInventoriesCount", Long.class)
                .getSingleResult();

        for(Inventory inv : inventories){

            List<History> histories = em.createNamedQuery("getAllHistories", History.class)
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

        em.close();

        request.setAttribute("inventories", inventories);
        request.setAttribute("inventories_count", inventories_count);
        request.setAttribute("page", page);

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/stocks/index.jsp");
        rd.forward(request, response);
    }
}