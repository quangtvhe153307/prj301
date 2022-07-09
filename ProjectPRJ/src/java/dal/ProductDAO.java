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
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author Admin
 */
public class ProductDAO extends DBContext {

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Products p  inner join Categories c on p.CategoryID = c.CategoryID";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDouble(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getProductByUser(String user) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Products where UserID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDouble(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> searchByName(String key, double from, double to, String orderOption) {
        List<Product> list = new ArrayList<>();
        String sql;
        if (orderOption.equals("1") || orderOption.equals("2")) {
            sql = "select * from Products p ";
        } else {
            sql = "select p.*, SUM(Quantity) as soldQuantity from Products p left join [Order Details] od on p.ProductID = od.ProductID ";
        }
        sql += "where p.ProductName like ? and p.UnitPrice between ? and ? ";
        if (orderOption.equals("1")) {
            sql += " order by UnitPrice asc, ProductID asc";
        } else if (orderOption.equals("2")) {
            sql += " order by UnitPrice desc, ProductID asc";
        } else if (orderOption.equals("3")) {
            sql += " group by p.ProductID, p.ProductName, p.UserID, p.CategoryID, p.QuantityPerUnit, p.UnitPrice, p.UnitsInStock, p.UnitsOnOrder, p.image, Brand order by soldQuantity asc, ProductID asc";
        } else {
            sql += " group by p.ProductID, p.ProductName, p.UserID, p.CategoryID, p.QuantityPerUnit, p.UnitPrice, p.UnitsInStock, p.UnitsOnOrder, p.image, Brand order by soldQuantity desc, ProductID asc";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + key + "%");
            st.setDouble(2, from);
            st.setDouble(3, to);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDouble(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> searchByName(String[] category, String key, double from, double to, String orderOption) {
        List<Product> list = new ArrayList<>();
        String sql;
        if (orderOption.equals("1") || orderOption.equals("2")) {
            sql = "select * from Products p ";
        } else {
            sql = "select p.*, SUM(Quantity) as soldQuantity from Products p left join [Order Details] od on p.ProductID = od.ProductID ";
        }
        sql += "where p.ProductName like ? and p.UnitPrice between ? and ? ";
        if (category != null) {
            sql += "and p.CategoryID in (";
            for (int i = 0; i < category.length; i++) {
                sql += category[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }
        if (orderOption.equals("1")) {
            sql += " order by UnitPrice asc, ProductID asc";
        } else if (orderOption.equals("2")) {
            sql += " order by UnitPrice desc, ProductID asc";
        } else if (orderOption.equals("3")) {
            sql += " group by p.ProductID, p.ProductName, p.UserID, p.CategoryID, p.QuantityPerUnit, p.UnitPrice, p.UnitsInStock, p.UnitsOnOrder, p.image, Brand order by soldQuantity asc, ProductID asc";
        } else {
            sql += " group by p.ProductID, p.ProductName, p.UserID, p.CategoryID, p.QuantityPerUnit, p.UnitPrice, p.UnitsInStock, p.UnitsOnOrder, p.image, Brand order by soldQuantity desc, ProductID asc";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + key + "%");
            st.setDouble(2, from);
            st.setDouble(3, to);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> checkBrandsProduct(String[] brand, int categoryId, double from, double to, String orderOption) {
        List<Product> list = new ArrayList<>();
        String sql;
        if (orderOption.equals("1") || orderOption.equals("2")) {
            sql = "select * from Products p where CategoryID = ? ";
        } else {
            sql = "select p.*, SUM(Quantity) as soldQuantity \n"
                    + "from Products p left join [Order Details] od \n"
                    + "on p.ProductID = od.ProductID where CategoryID = ? ";
        }

        if (brand != null) {
            sql += "and Brand in (";
            for (int i = 0; i < brand.length; i++) {
                sql += "N'" + brand[i] + "'" + ",";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);
            }
            sql += ")";
        }
        sql += " and p.UnitPrice between " + from + " and " + to;
        if (orderOption.equals("1")) {
            sql += "order by UnitPrice asc, ProductID asc";
        } else if (orderOption.equals("2")) {
            sql += "order by UnitPrice desc, ProductID asc";
        } else if (orderOption.equals("3")) {
            sql += "group by p.ProductID, p.ProductName, UserID, CategoryID, QuantityPerUnit, p.UnitPrice, UnitsInStock, UnitsOnOrder, image, Brand \n"
                    + "order by soldQuantity asc, ProductID asc";
        } else {
            sql += "group by p.ProductID, p.ProductName, UserID, CategoryID, QuantityPerUnit, p.UnitPrice, UnitsInStock, UnitsOnOrder, image, Brand \n"
                    + "order by soldQuantity desc, ProductID asc";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, categoryId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDouble(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> checkBrandsProduct(String[] brand, int categoryId, double from, double to, String orderOption, String key) {
        List<Product> list = new ArrayList<>();
        String sql;
        if (orderOption.equals("1") || orderOption.equals("2")) {
            sql = "select * from Products p where CategoryID = ? ";
        } else {
            sql = "select p.*, SUM(Quantity) as soldQuantity \n"
                    + "from Products p left join [Order Details] od \n"
                    + "on p.ProductID = od.ProductID where CategoryID = ? ";
        }

        if (brand != null) {
            sql += "and Brand in (";
            for (int i = 0; i < brand.length; i++) {
                sql += "N'" + brand[i] + "'" + ",";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);
            }
            sql += ")";
        }
        sql += " and p.UnitPrice between " + from + " and " + to;
        sql += "and p.ProductName like ? ";
        if (orderOption.equals("1")) {
            sql += "order by UnitPrice asc, ProductID asc";
        } else if (orderOption.equals("2")) {
            sql += "order by UnitPrice desc, ProductID asc";
        } else if (orderOption.equals("3")) {
            sql += "group by p.ProductID, p.ProductName, UserID, CategoryID, QuantityPerUnit, p.UnitPrice, UnitsInStock, UnitsOnOrder, image, Brand \n"
                    + "order by soldQuantity asc, ProductID asc";
        } else {
            sql += "group by p.ProductID, p.ProductName, UserID, CategoryID, QuantityPerUnit, p.UnitPrice, UnitsInStock, UnitsOnOrder, image, Brand \n"
                    + "order by soldQuantity desc, ProductID asc";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, categoryId);
            st.setString(2, "%" + key + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDouble(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> checkBrandsProduct(String[] brand, int categoryId, double from, double to) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Products where CategoryID = ? ";

        if (brand != null) {
            sql += "and Brand in (";
            for (int i = 0; i < brand.length; i++) {
                sql += "N'" + brand[i] + "'" + ",";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);
            }
            sql += ")";
        }
        sql += " and UnitPrice between " + from + " and " + to;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, categoryId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDouble(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Product getProductByID(int id) {
        String sql = "select * from Products where ProductID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            DecimalFormat df = new DecimalFormat(sql);
            Product p = null;
            if (rs.next()) {
                p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDouble(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10));

            }
            return p;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Product getProductByIDAndUserId(int id, String userid) {
        String sql = "select * from Products where ProductID=? and UserID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, userid);
            ResultSet rs = st.executeQuery();
            Product p = null;
            if (rs.next()) {
                p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDouble(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10));

            }
            return p;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Product> getListByPage(List<Product> list, int start, int end) {
        ArrayList<Product> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public List<Product> getBestSellers() {
        List<Product> list = new ArrayList<>();
        String sql = "select top 8 p.*\n"
                + "from Products p\n"
                + "inner join [Order Details] od \n"
                + "on p.ProductID = od.ProductID\n"
                + "order by Quantity desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDouble(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> top10Product() {
        List<Product> list = new ArrayList<>();
        String sql = "select top 10 p.*\n"
                + "from Products p\n"
                + "inner join [Order Details] od \n"
                + "on p.ProductID = od.ProductID\n"
                + "order by Quantity desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDouble(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getProductsByCategoryId(int catId) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Products where CategoryID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, catId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDouble(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public int getSoldQuantity(int productid) {
        String sql = "select p.ProductID, SUM(Quantity) as soldQuantity from Products p inner join [Order Details] od on p.ProductID = od.ProductID\n"
                + "where p.ProductID = ?  group by p.ProductID";
        int result = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getInt(2);
            }
            return result;
        } catch (SQLException e) {

        }
        return result;
    }

    public List<String> getBrandsByCategoryId(int catid) {
        String sql = "select distinct Brand from Products where CategoryID = ? order by Brand";
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, catid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public double getHighestPrice() {
        String sql = "selecgfdgdfgttt top 1 UnitPrice from Products order by UnitPrice desc";
        double result = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getDouble(1);
            }
        } catch (SQLException e) {

        }
        return result;
    }

    public void update(Product c) {
        String sql = "update Products\n"
                + "set ProductName=?, QuantityPerUnit=?, UnitPrice=?, UnitsInStock=?, image=?, Brand=? where ProductID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getName());
            st.setString(2, c.getQuantityPerUnit());
            st.setDouble(3, c.getPrice());
            st.setInt(4, c.getUnitsInStock());
            st.setString(5, c.getImage());
            st.setString(6, c.getBrand());
            st.setInt(7, c.getProductID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(int id) {
//        delete from Products where ProductID = 1
//delete from Feedbacks where ProductID = 1
//delete from ProductImages where ProductID = 1
        String sql1 = "delete from Feedbacks where ProductID =?";
        String sql2 = "delete from ProductImages where ProductID =?";
        String sql3 = "delete from Products where ProductID =?";
        try {
            PreparedStatement st1 = connection.prepareStatement(sql1);
            PreparedStatement st2 = connection.prepareStatement(sql2);
            PreparedStatement st3 = connection.prepareStatement(sql3);
            st1.setInt(1, id);
            st2.setInt(1, id);
            st3.setInt(1, id);
            st1.executeUpdate();
            st2.executeUpdate();
            st3.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insert(Product c) {
        String sql = "insert into Products values\n"
                + "(?, ?, ?, ?,?, ?, ?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getName());
            st.setString(2, c.getUserID());
            st.setInt(3, c.getCategoryID());
            st.setString(4, c.getQuantityPerUnit());
            st.setDouble(5, c.getPrice());
            st.setInt(6, c.getUnitsInStock());
            st.setInt(7, 0);
            st.setString(8, c.getImage());
            st.setString(9, c.getBrand());
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public int getTotalProducts() {
        String sql = "select COUNT(*) from Products";
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

    public static void main(String[] args) {
        ProductDAO d = new ProductDAO();
        System.out.println(d.getHighestPrice());
    }

//    }
}
