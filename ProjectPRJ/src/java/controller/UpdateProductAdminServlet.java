/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import model.Item;
import model.Product;

/**
 *
 * @author Admin
 */
public class UpdateProductAdminServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateProductAdminServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProductAdminServlet at " + request.getContextPath() + "</h1>");
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
        response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a == null) {
            response.sendRedirect("login");
        } else {
            ProductDAO proDAO = new ProductDAO();


            PrintWriter out = response.getWriter();
            String proid_raw = request.getParameter("productid");
            int proId;
            try {
                proId = Integer.parseInt(proid_raw);
                if (a.getRole() != 3) {
                    out.println("access denied");
                } else {
                    Product p = proDAO.getProductByID(proId);
                    request.setAttribute("product", p);
                    request.getRequestDispatcher("updateproductadmin.jsp").forward(request, response);
                }

            } catch (IOException e) {

            }
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
        String productid_raw = request.getParameter("productid");
        String categoryid_raw = request.getParameter("categoryid");
        String productName = request.getParameter("name");
        String price_raw = request.getParameter("price");
        String quantityPerUnit = request.getParameter("quantityPerUnit");
        String img = request.getParameter("img");
        String unitsInStock_raw = request.getParameter("unitsInStock");
        String brand = request.getParameter("brand");
        int productid, categoryid, unitsInStock;
        double price;
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        try {
            productid = Integer.parseInt(productid_raw);
            categoryid = Integer.parseInt(categoryid_raw);
            unitsInStock = Integer.parseInt(unitsInStock_raw);
            price = Double.parseDouble(price_raw);
            Product p = new Product(productid, productName, a.getUsername(), categoryid, quantityPerUnit, price, unitsInStock, 0, img, brand);
            ProductDAO pd = new ProductDAO();
            pd.update(p);




            response.sendRedirect("productmanageadmin");

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
