/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import dal.DBContext;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class CategoryDAO extends DBContext {

    //lay tat du lieu tu bang Categories
    //select * from Categories
    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        String sql = "select * from Categories";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setCatID(rs.getInt(1));
                c.setCatName(rs.getString(2));
                c.setDescribe(rs.getString(3));
                c.setPicture(rs.getString(4));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public int getTotalCategory() {
        String sql = "select COUNT(*) from Categories";
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

    //delete
    //delete from Categories where id=4
    public void delete(int id) {
        String sql = "delete from Categories where CategoryID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Category getCategoryByID(int id) {
        String sql = "select * from Categories where CategoryID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Category c = new Category(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //insert
    public void insert(Category c) {
        String sql = "insert into Categories values (?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getCatName());
            st.setString(2, c.getDescribe());
            st.setString(3, c.getPicture());
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    //update update Categories
//set name='ABCD', describe='sfakjshfkas' where id=9
    public void update(Category c) {
        String sql = "update Categories set name=?, describe=? where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getCatName());
            st.setString(2, c.getDescribe());
            st.setInt(3, c.getCatID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        CategoryDAO c = new CategoryDAO();
        List<Category> list = c.getAll();
        System.out.println(list.get(0).getCatName());
    }
}
