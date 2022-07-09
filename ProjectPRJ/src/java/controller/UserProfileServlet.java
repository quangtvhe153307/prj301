/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
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
import util.Util;

/**
 *
 * @author Admin
 */
public class UserProfileServlet extends HttpServlet {

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
            out.println("<title>Servlet UserProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserProfileServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession sesion = request.getSession();
        Account a = (Account) sesion.getAttribute("account");
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
            request.getRequestDispatcher("profile.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String contact = request.getParameter("contact");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        AccountDAO ad = new AccountDAO();
        Account a1 = ad.searchByEmail(email);
        String message;
        if (a1 == null || a1.getUsername().equals(username)) {
            if (!Util.isValidName(contact)) {
                message = "Invalid contact name";
            } else if (!Util.isValidPhoneNumber(phone)) {
                message = "Invalid phone number";
            } else if (!Util.isValidCity(city)) {
                message = "Invalid city";
            } else if (!Util.isValidAddress(address)) {
                message = "Invalid address";
            } else {
                Account a = new Account();
                a.setAddress(address);
                a.setUsername(username);
                a.setCity(city);
                a.setEmail(email);
                a.setContactName(contact);
                a.setPhone(phone);
                ad.update(a);

                a = ad.searchByUserID(username);

                session.setAttribute("account", a);
                message = "Successfully";
            }
            request.setAttribute("message", message);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            message = "Email already exists!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
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
