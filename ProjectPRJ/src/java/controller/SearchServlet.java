/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;
import model.Category;
import model.Item;
import model.Product;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {

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
            out.println("<title>Servlet SearchServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchServlet at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("utf-8");

        String from_raw = request.getParameter("from");
        String to_raw = request.getParameter("to");
        String key = request.getParameter("key");
        String orderByOption = request.getParameter("sortBy");
        String[] categoryFilter = request.getParameterValues("categoryFilter");
        double from, to;
        boolean[] checked;
        if (orderByOption == null) {
            orderByOption = "1";
        }
        try {
            ProductDAO d = new ProductDAO();

            if (from_raw == null) {
                from = 0;
            } else {
                from = Double.parseDouble(from_raw);
            }
            if (to_raw == null) {
                to = d.getHighestPrice();
            } else {
                to = Double.parseDouble(to_raw);
            }
//            List<Product> list1 = d.searchByName(key, from, to, orderByOption);
            List<Product> list1 = d.searchByName(categoryFilter, key, from, to, orderByOption);

            CategoryDAO cd = new CategoryDAO();
            List<Category> listCate = cd.getAll();

            checked = new boolean[listCate.size()];
            for (int i = 0; i < checked.length; i++) {
                if (isCheck(listCate.get(i).getCatID() + "", categoryFilter)) {
                    checked[i] = true;
                } else {
                    checked[i] = false;
                }
            }

////            paging
            int page, numperpage = 8;
            int num;
            if (list1.size() % numperpage == 0) {
                num = list1.size() / numperpage;
            } else {
                num = list1.size() / numperpage + 1;
            }
            String xpage = request.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start, end;
            start = (page - 1) * numperpage;
            end = Math.min(page * numperpage, list1.size());
            List<Product> list = d.getListByPage(list1, start, end);

            List<Product> allProducts = d.getAll();
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

            request.setAttribute("checked", checked);
            request.setAttribute("cateList", listCate);
            request.setAttribute("num", num);
            request.setAttribute("page", page);
            request.setAttribute("orderOption", orderByOption);
            request.setAttribute("from", from);
            request.setAttribute("to", to);
            request.setAttribute("ProductList", list);
            request.setAttribute("key", key);
            request.getRequestDispatcher("search.jsp").forward(request, response);
        } catch (NumberFormatException e) {

        }
    }

    private boolean isCheck(String b, String[] brand) {
        if (brand == null) {
            return false;
        } else {
            for (int i = 0; i < brand.length; i++) {
                if (brand[i].equals(b)) {
                    return true;
                }
            }
            return false;
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
        processRequest(request, response);
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
