/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Shop;

/**
 *
 * @author Admin
 */
public class ShopDAO extends DBContext {

    public void insert(Shop s) {
        String sql = "insert into Shops values \n"
                + "(?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, s.getUserid());
            st.setString(2, s.getShopName());
            st.setString(3, s.getDes());
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public int getTotalShop() {
        String sql = "select COUNT(*) from Shops";
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

    public List<Shop> topSeller() {
        List<Shop> list = new ArrayList<>();
        String sql = "select top 5 s.UserID, s.ShopName, SUM(od.Quantity)\n"
                + "from Shops s left join Products p\n"
                + "on s.UserID = p.UserID\n"
                + "left join [Order Details] od\n"
                + "on p.ProductID = od.ProductID\n"
                + "group by s.UserID, s.ShopName\n"
                + "order by SUM(od.Quantity) desc";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Shop c = new Shop();
                c.setUserid(rs.getString(1));
                c.setShopName(rs.getString(2));
                list.add(c);
            }
        } catch(SQLException e){
            
        }
        return list;
    }
}
