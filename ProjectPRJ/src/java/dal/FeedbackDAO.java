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
import model.Feedback;

/**
 *
 * @author Admin
 */
public class FeedbackDAO extends DBContext {

    public List<Feedback> getFeedbackByProduct(int productid) {
        List<Feedback> list = new ArrayList<>();
        String sql = "select * from Feedbacks where ProductID = ? order by [date] desc, FeedbackID asc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Feedback(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public int getTotalFeedback() {
        String sql = "select COUNT(*) from Feedbacks";
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

    public int countFeedbackByProduct(int productid) {
        int result = 0;
        String sql = "select COUNT(*) from Feedbacks where ProductID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public List<Feedback> getListByPage(List<Feedback> list, int start, int end) {
        ArrayList<Feedback> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public void insert(Feedback f) {
        String sql = "insert into Feedbacks values (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, f.getProductID());
            st.setString(2, f.getContent());
            st.setString(3, f.getUserID());
            st.setString(4, f.getDate());
            st.executeUpdate();
        } catch (SQLException e) {

        }

    }

    public static void main(String[] args) {
        FeedbackDAO f = new FeedbackDAO();
        System.out.println(f.countFeedbackByProduct(2));
    }
}
