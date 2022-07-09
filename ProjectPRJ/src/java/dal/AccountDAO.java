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
import model.Account;

/**
 *
 * @author Admin
 */
public class AccountDAO extends DBContext {

    public Account check(String user, String pass) {
        String sql = "select * from Accounts where UserID=? and Password=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Account(user, pass, rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        String sql = "select * from Accounts";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account c = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Account check2(String user, String email) {
        String sql = "select * from Accounts where UserID=? and Email=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Account(user, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public Account searchByUserID(String user) {
        String sql = "select * from Accounts where UserID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Account(user, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public Account searchByEmail(String email) {
        String sql = "select * from Accounts where Email=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public Account searchByEmailAndUserName(String email, String user) {
        String sql = "select * from Accounts where Email =? and UserID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, user);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
            }
        } catch (SQLException e) {

        }
        return null;
    }
//    insert into Accounts (UserID, Password) values ('abc','abc')

    public void register(String user, String pass, String email) {
        String sql = "insert into Accounts (UserID, Password, Email) values (?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            st.setString(3, email);
            st.executeUpdate();
        } catch (SQLException e) {

        }

    }

    public void update(Account c) {
        String sql = "update Accounts\n"
                + "set ContactName=?, Address=?, City=?, Phone=?, Email=? where UserID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getContactName());
            st.setString(2, c.getAddress());
            st.setString(3, c.getCity());
            st.setString(4, c.getPhone());
            st.setString(5, c.getEmail());
            st.setString(6, c.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void chgpwd(Account c) {
        String sql = "update Accounts\n"
                + "set Password = ? where UserID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getPassword());
            st.setString(2, c.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void SellerRegister(String user) {
        String sql = "update Accounts set role = 2 where UserID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public int getTotalAccount() {
        String sql = "select COUNT(*) from Accounts";
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

    public List<Account> getListByPage(List<Account> list, int start, int end) {
        ArrayList<Account> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public static void main(String[] args) {
        AccountDAO d = new AccountDAO();
        System.out.println(d.getTotalAccount());
    }
}
