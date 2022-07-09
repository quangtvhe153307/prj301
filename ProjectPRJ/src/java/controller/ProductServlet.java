/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.FeedbackDAO;
import dal.ProductDAO;
import dal.ProductImageDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.Feedback;
import model.Item;
import model.Product;

/**
 *
 * @author Admin
 */
public class ProductServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String productid_raw = request.getParameter("productid");
        ProductDAO pd = new ProductDAO();
        FeedbackDAO fd = new FeedbackDAO();
        ProductImageDAO pid = new ProductImageDAO();
        int productid;
        try {
            productid = Integer.parseInt(productid_raw);
            //product
            Product p = pd.getProductByID(productid);
            //soldquan
            int soldQuan = pd.getSoldQuantity(productid);
            //countFeedback
            int count = fd.countFeedbackByProduct(productid);
            //feedbacklist
            List<Feedback> feedbackList0 = fd.getFeedbackByProduct(productid);
            int page, numperpage = 5;
            int num;

            if (feedbackList0.size() % numperpage == 0) {
                if (feedbackList0.isEmpty()) {
                    num = 1;
                } else {

                    num = feedbackList0.size() / numperpage;
                }
            } else {
                num = feedbackList0.size() / numperpage + 1;
            }
            String xpage = request.getParameter("commentPage");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start, end;
            start = (page - 1) * numperpage;
            end = Math.min(page * numperpage, feedbackList0.size());
            List<Feedback> feedbackList = fd.getListByPage(feedbackList0, start, end);

            //productfromshop
            List<Product> list1 = pd.getProductByUser(p.getUserID());
            List<Product> thisShopProduct = pd.getListByPage(list1, 0, Math.min(6, list1.size()));

            //relatedproduct
            List<Product> list2 = pd.getProductsByCategoryId(p.getCategoryID());
            List<Product> relatedProduct = pd.getListByPage(list2, 0, Math.min(6, list2.size()));

            //imageList
            List<String> image = pid.getImagesByProduct(productid);

            List<Product> allProducts = pd.getAll();
            Cookie[] arr = request.getCookies();
            String txt = "";
            if (arr != null) {
                for (Cookie i : arr) {
                    if (i.getName().equals("cart")) {
                        txt += i.getValue();
                    }
                }
            }
            Cart cart = new Cart(txt, allProducts);
            List<Item> listItem = cart.getItems();
            int cartSize;
            if (listItem != null) {
                cartSize = listItem.size();
            } else {
                cartSize = 0;
            }
            request.setAttribute("cart", cart);
            request.setAttribute("cartSize", cartSize);

            request.setAttribute("product", p);
            request.setAttribute("imageList", image);
            request.setAttribute("soldQuan", soldQuan);
            session.setAttribute("feedback", feedbackList);
            request.setAttribute("countFeedback", count);
            request.setAttribute("page", page);
            request.setAttribute("num", num);
            request.setAttribute("productFromShop", thisShopProduct);
            request.setAttribute("relatedProduct", relatedProduct);

            request.getRequestDispatcher("product.jsp").forward(request, response);
        } catch (IOException e) {

        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
