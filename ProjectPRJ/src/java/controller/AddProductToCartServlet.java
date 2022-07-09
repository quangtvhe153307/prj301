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
public class AddProductToCartServlet extends HttpServlet {

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
            out.println("<title>Servlet AddProductToCartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProductToCartServlet at " + request.getContextPath() + "</h1>");
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
            ProductDAO proDAO = new ProductDAO();
            List<Product> allProducts = proDAO.getAll();
            Cookie[] arr = request.getCookies();
            String txt = "";
            if (arr != null) {
                for (Cookie i : arr) {
                    if (i.getName().equals("cart")) {
                        txt += i.getValue();
                        i.setMaxAge(0);
                        response.addCookie(i);
                    }
                }
            }
            String num_raw = request.getParameter("num");
            String id_raw = request.getParameter("id");
            int proid, num;
            int maxQuan, curQuan = 0;
            Cart cart = new Cart(txt, allProducts);
            try {
                proid = Integer.parseInt(id_raw);
                num = Integer.parseInt(num_raw);
                for (Item i : cart.getItems()) {
                    if (i.getProduct().getProductID() == proid) {
                        curQuan = i.getQuantity();
                    }
                }
                maxQuan = proDAO.getProductByID(proid).getUnitsInStock();
                if ((curQuan + num) < maxQuan) {
                    maxQuan = num;
                } else {
                    maxQuan = maxQuan - curQuan;
                }
                if (txt.isEmpty()) {
                    txt = id_raw + ":" + maxQuan;
                } else {
                    txt += "," + id_raw + ":" + maxQuan;
                }
                Cookie c = new Cookie("cart", txt);
                c.setMaxAge(2 * 24 * 60 * 60);
                response.addCookie(c);
                response.sendRedirect("product?productid=" + id_raw);
            } catch (NumberFormatException e) {

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
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a == null) {
            response.sendRedirect("login");
        } else {
            ProductDAO proDAO = new ProductDAO();
            List<Product> allProducts = proDAO.getAll();
            Cookie[] arr = request.getCookies();
            String txt = "";
            if (arr != null) {
                for (Cookie i : arr) {
                    if (i.getName().equals("cart")) {
                        txt += i.getValue();
                        i.setMaxAge(0);
                        response.addCookie(i);
                    }
                }
            }
            String num = request.getParameter("num");
            String id = request.getParameter("id");
            if (txt.isEmpty()) {
                txt = id + ":" + num;
            } else {
                txt += "," + id + ":" + num;
            }
            Cookie c = new Cookie("cart", txt);
            c.setMaxAge(2 * 24 * 60 * 60);
            response.addCookie(c);
            response.sendRedirect("cart");
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
