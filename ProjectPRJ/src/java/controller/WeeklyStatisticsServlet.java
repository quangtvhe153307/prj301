/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author Admin
 */
public class WeeklyStatisticsServlet extends HttpServlet {

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
            out.println("<title>Servlet WeeklyStatisticsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WeeklyStatisticsServlet at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        if(a.getRole() != 3){
            out.println("access denied");
        } else {
            OrderDAO d = new OrderDAO();
            DecimalFormat df = new DecimalFormat("#.##");
            double monday = d.countOrderByWeekDay("monday");
            double tuesday = d.countOrderByWeekDay("tuesday");
            double wednesday = d.countOrderByWeekDay("wednesday");
            double thursday = d.countOrderByWeekDay("thursday");
            double friday = d.countOrderByWeekDay("friday");
            double sunday = d.countOrderByWeekDay("sunday");
            double saturday = d.countOrderByWeekDay("saturday");
            request.setAttribute("monday", df.format(monday));
            request.setAttribute("tuesday",  df.format(tuesday));
            request.setAttribute("wednesday", df.format(wednesday));
            request.setAttribute("thursday",  df.format(thursday));
            request.setAttribute("friday",  df.format(friday));
            request.setAttribute("sunday",  df.format(sunday));
            request.setAttribute("saturday",  df.format(saturday));

            request.getRequestDispatcher("weeklystatistic.jsp").forward(request, response);
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
