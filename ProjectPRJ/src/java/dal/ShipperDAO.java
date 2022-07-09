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
import model.Shipper;

/**
 *
 * @author Admin
 */
public class ShipperDAO extends DBContext {

    public List<Shipper> getAll() {
        List<Shipper> list = new ArrayList<>();
        String sql = "select * from Shippers";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Shipper c = new Shipper(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Shipper getShipperById(int id) {
        String sql = "select * from Shippers where ShipperID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Shipper c = new Shipper(rs.getInt(1), rs.getString(2), rs.getString(3));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
