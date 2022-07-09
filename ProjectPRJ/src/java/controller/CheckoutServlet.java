/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.OrderDAO;
import dal.ProductDAO;
import dal.ShipperDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import model.Shipper;
import util.Util;

/**
 *
 * @author Admin
 */
public class CheckoutServlet extends HttpServlet {

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
            out.println("<title>Servlet CheckoutServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckoutServlet at " + request.getContextPath() + "</h1>");
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
        ProductDAO proDAO = new ProductDAO();
        if (a == null) {
            response.sendRedirect("login");
        } else {
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
            ShipperDAO sd = new ShipperDAO();
            List<String> providerList = cart.getUserList();
            List<Shipper> shipList = sd.getAll();
            request.setAttribute("shipList", shipList);
            request.setAttribute("providerList", providerList);
            request.setAttribute("cart", cart);
            request.setAttribute("cartSize", cartSize);
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        OrderDAO od = new OrderDAO();
        if (a == null) {
            response.sendRedirect("login");
        } else {

            String shipName = request.getParameter("name");
            String shipPhone = request.getParameter("phone");
            String shipAddress = request.getParameter("address");
            String shipCity = request.getParameter("city");
            String shipEmail = request.getParameter("email");
            String shipid_raw = request.getParameter("shipper");
            int shipid;
            ShipperDAO sp = new ShipperDAO();
            String message = "";
            if (!Util.isValidName(shipName)) {
                message = "Invalid name";
                List<Item> listItem = cart.getItems();
                int cartSize;
                if (listItem != null) {
                    cartSize = listItem.size();
                } else {
                    cartSize = 0;
                }
                ShipperDAO sd = new ShipperDAO();
                List<String> providerList = cart.getUserList();
                List<Shipper> shipList = sd.getAll();
                request.setAttribute("message", message);
                request.setAttribute("shipList", shipList);
                request.setAttribute("providerList", providerList);
                request.setAttribute("cart", cart);
                request.setAttribute("cartSize", cartSize);
                request.getRequestDispatcher("checkout.jsp").forward(request, response);

            } else if (!Util.isValidPhoneNumber(shipPhone)) {
                message = "Invalid phone number";
                List<Item> listItem = cart.getItems();
                int cartSize;
                if (listItem != null) {
                    cartSize = listItem.size();
                } else {
                    cartSize = 0;
                }
                ShipperDAO sd = new ShipperDAO();
                List<String> providerList = cart.getUserList();
                List<Shipper> shipList = sd.getAll();
                request.setAttribute("message", message);
                request.setAttribute("shipList", shipList);
                request.setAttribute("providerList", providerList);
                request.setAttribute("cart", cart);
                request.setAttribute("cartSize", cartSize);
                request.getRequestDispatcher("checkout.jsp").forward(request, response);
            } else if (!Util.isValidAddress(shipAddress)) {
                message = "Invalid address";
                List<Item> listItem = cart.getItems();
                int cartSize;
                if (listItem != null) {
                    cartSize = listItem.size();
                } else {
                    cartSize = 0;
                }
                ShipperDAO sd = new ShipperDAO();
                List<String> providerList = cart.getUserList();
                List<Shipper> shipList = sd.getAll();
                request.setAttribute("message", message);
                request.setAttribute("shipList", shipList);
                request.setAttribute("providerList", providerList);
                request.setAttribute("cart", cart);
                request.setAttribute("cartSize", cartSize);
                request.getRequestDispatcher("checkout.jsp").forward(request, response);
            } else if (!Util.isValidCity(shipCity)) {
                message = "Invalid city";
                List<Item> listItem = cart.getItems();
                int cartSize;
                if (listItem != null) {
                    cartSize = listItem.size();
                } else {
                    cartSize = 0;
                }
                ShipperDAO sd = new ShipperDAO();
                List<String> providerList = cart.getUserList();
                List<Shipper> shipList = sd.getAll();
                request.setAttribute("message", message);
                request.setAttribute("shipList", shipList);
                request.setAttribute("providerList", providerList);
                request.setAttribute("cart", cart);
                request.setAttribute("cartSize", cartSize);
                request.getRequestDispatcher("checkout.jsp").forward(request, response);
            } else {
                //create an instance of Properties Class   
//            Properties props = new Properties();
                /* Specifies the IP address of your default mail server
     	   for e.g if you are using gmail server as an email sever
           you will pass smtp.gmail.com as value of mail.smtp host. 
           As shown here in the code. 
           Change accordingly, if your email id is not a gmail id
                 */
//            props.put("mail.smtp.host", "smtp.gmail.com");
//            //below mentioned mail.smtp.port is optional
//            props.put("mail.smtp.port", "587");
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.starttls.enable", "true");
//            String user = "quangtvhe153307@fpt.edu.vn";
//            String pass = "Nogiver1";
//            Session sess = Session.getInstance(props, new javax.mail.Authenticator() {
//                @Override
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(user, pass);
//                }
//            });
                try {

                    //crud sql
                    shipid = Integer.parseInt(shipid_raw);
                    od.addOrder(a, cart, shipid, shipName, shipAddress, shipCity, shipPhone);

                    //getShiper
                    Shipper s = sp.getShipperById(shipid);

                    //add cookie to browser
                    Cookie c = new Cookie("cart", "");
                    c.setMaxAge(0);
                    response.addCookie(c);

//                DecimalFormat df = new DecimalFormat("#.##");
//                //message
//                String product = "";
//                for (Item i : cart.getItems()) {
//                    product += "<div>" + i.getProduct().getName() + " | Quantity: " + i.getQuantity() + " | Price: " + df.format(i.getPrice()) + "đ</div>";
//                }
//                String msg = "<!DOCTYPE html>\n"
//                        + "<html>\n"
//                        + "    <head>\n"
//                        + "        <title>TODO supply a title</title>\n"
//                        + "        <meta charset=\"UTF-8\">\n"
//                        + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
//                        + "    </head>\n"
//                        + "    <body>\n"
//                        + "\n"
//                        + "        <h3 style=\"color: blue;\">You just ordered from PRJ Shop.</h3>\n"
//                        + "        <div>Full Name :" + shipName + "</div>\n"
//                        + "        <div>Phone : " + shipPhone + "</div>\n"
//                        + "        <div>Address : " + shipAddress + "</div>\n"
//                        + "        <div>City : " + shipCity + "</div>\n"
//                        + "        <div>Shipping unit : " + s.getCompanyName() + "</div>\n"
//                        + "        <div>Your products:</div>\n"
//                        + product
//                        + "        <div>Total money: " + df.format(cart.getTotalMoney()) + "đ</div>\n"
//                        + "        <h3 style=\"color: blue;\">Thank you very much!</h3>\n"
//                        + "\n"
//                        + "    </body>\n"
//                        + "</html>\n"
//                        + "";
//                /* Create an instance of MimeMessage, 
// 	      it accept MIME types and headers 
//                 */
//                MimeMessage message = new MimeMessage(sess);
//                message.setFrom(new InternetAddress(user));
//                message.addRecipient(Message.RecipientType.TO, new InternetAddress(shipEmail));
//                message.setSubject("Order confirmation ");
//
//                message.setText(msg, "utf-8", "html");

                    /* Transport class is used to deliver the message to the recipients */
//                Transport.send(message);
                    response.sendRedirect("home");
                } catch (IOException | NumberFormatException e) {

                }
            }

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
