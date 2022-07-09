/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Cart;
import model.Item;
import model.Order;

/**
 *
 * @author Admin
 */
public class OrderDAO extends DBContext {

    public void addOrder(Account a, Cart cart, int shipVia, String shipName, String shipAddress, String shipCity, String phone) {
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();
        try {
            //add order
            String sql = "insert into Orders (UserID,OrderDate, ShipVia, Freight, ShipName, ShipAddress, ShipCity, Phone) values (?,?,?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, a.getUsername());
            st.setString(2, date);
            st.setInt(3, shipVia);
            st.setDouble(4, cart.getTotalMoney());
            st.setString(5, shipName);
            st.setString(6, shipAddress);
            st.setString(7, shipCity);
            st.setString(8, phone);
            st.executeUpdate();

            //lay id cua order vua add
            String sql1 = "select top 1 OrderID from Orders order by OrderID desc";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();
            ///add order detail
            if (rs.next()) {
                int id = rs.getInt(1);
                for (Item i : cart.getItems()) {
                    String sql2 = "insert into [Order Details] values(?,?,?,?,?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setInt(1, id);
                    st2.setInt(2, i.getProduct().getProductID());
                    st2.setDouble(3, i.getPrice());
                    st2.setInt(4, i.getQuantity());
                    st2.setDouble(5, 0);
                    st2.executeUpdate();
                }
            }

            //cap nhat sl san phan
            String sql3 = "update Products set UnitsInStock = UnitsInStock-? where ProductID=?";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            for (Item i : cart.getItems()) {
                st3.setInt(1, i.getQuantity());
                st3.setInt(2, i.getProduct().getProductID());
                st3.executeUpdate();
            }
        } catch (SQLException e) {

        }
    }

    public double getTotalSales() {
        String sql = "select SUM(Freight) from Orders";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {

        }
        return 0;
    }

    public int getTotalOrders() {
        String sql = "select count(OrderID) from Orders";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {

        }
        return 0;
    }

    public List<Order> getAll() {
        List<Order> list = new ArrayList<>();
        String sql = "select * from Orders order by OrderDate desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order c = new Order(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getDouble(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Order> getListByPage(List<Order> list, int start, int end) {
        ArrayList<Order> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public double countOrderByWeekDay(String day) {
        String sql = "select SUM(Freight) from Orders where DATENAME(WEEKDAY,OrderDate ) = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, day);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {

        }
        return 0;
    }
    public double saleByMonth(int month) {
        String sql = "select SUM(Freight) from Orders where MONTH(OrderDate) = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, month);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {

        }
        return 0;
    }

    public static void main(String[] args) {
        OrderDAO d = new OrderDAO();
        System.out.println(d.getAll().get(30).getFreight());
    }
}
