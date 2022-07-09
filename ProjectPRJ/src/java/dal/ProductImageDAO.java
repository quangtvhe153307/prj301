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

/**
 *
 * @author Admin
 */
public class ProductImageDAO extends DBContext{
        public List<String> getImagesByProduct(int productid) {
        List<String> list = new ArrayList<>();
        String sql = "select * from ProductImages where ProductID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                list.add(rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
