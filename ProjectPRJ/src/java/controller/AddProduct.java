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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Cart;
import model.Category;
import model.Item;
import model.Product;

/**
 *
 * @author Admin
 */
public class AddProduct extends HttpServlet {

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
            out.println("<title>Servlet AddProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProduct at " + request.getContextPath() + "</h1>");
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
        Account a = (Account) session.getAttribute("account");
        if (a == null) {
            response.sendRedirect("login");
        } else {
            ProductDAO pd = new ProductDAO();
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
            CategoryDAO cd = new CategoryDAO();
            List<Category> list = cd.getAll();
            request.setAttribute("list", list);

            request.getRequestDispatcher("addproduct.jsp").forward(request, response);
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
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        String name = request.getParameter("name");
        String catid_raw = request.getParameter("categoryid");
        String price_raw = request.getParameter("price");
        String quantityPerUnit = request.getParameter("quantityPerUnit");
        String img = request.getParameter("img");
        String unitsInStock_raw = request.getParameter("unitsInStock");
        String brand = request.getParameter("brand");
        int catid, quan;
        double price;
        try {
            catid = Integer.parseInt(catid_raw);
            quan = Integer.parseInt(unitsInStock_raw);
            price = Double.parseDouble(price_raw);
            ProductDAO pd = new ProductDAO();
            Product p = new Product(0, name, a.getUsername(), catid, quantityPerUnit, price, quan, 0, img, brand);
            pd.insert(p);
            
//            getcategorylisst
            CategoryDAO cd = new CategoryDAO();
            List<Category> list = cd.getAll();
            request.setAttribute("list", list);
            
            String message = "Successful";
            request.setAttribute("message", message);
            
//            //getCart
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
            
            
            request.getRequestDispatcher("addproduct.jsp").forward(request, response);
        } catch (NumberFormatException e) {

        }
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
