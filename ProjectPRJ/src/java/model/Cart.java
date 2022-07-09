/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dal.ProductDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Cart {
    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }
    public List<Item> getItems(){
        return items;
    }
    public int getQuantityById(int id){
        return getItemById(id).getQuantity();
    }
    private Item getItemById(int id){
        for (Item i : items) {
            if(i.getProduct().getProductID() == id){
                return i;
            }
        }
        return null;
    }
    public void addItem(Item t){
        if(getItemById(t.getProduct().getProductID()) != null){
            Item m = getItemById(t.getProduct().getProductID());
            m.setQuantity(m.getQuantity()+t.getQuantity());
        } else {
            items.add(t);
        }
    }
    public void removeItem(int id){
        if(getItemById(id) != null){
            items.remove(getItemById(id));
        }
    }
    public double getTotalMoney(){
        double t = 0;
        for (Item i : items) {
            t += i.getQuantity() * i.getPrice();
        }
        return t;
    }
    private Product getProductById(int id, List<Product> list){
        for (Product i : list) {
            if(i.getProductID() == id){
                return i;
            }
        }
        return null;
    }
    public Cart(String txt, List<Product> list){
        items = new ArrayList<>();
        try{
            if(txt != null && txt.length() != 0){
                String[] s =txt.split(",");
                for (String i : s) {
                    String[] n =i.split(":");
                    int id = Integer.parseInt(n[0]);
                    int quantity = Integer.parseInt(n[1]);
                    Product p = getProductById(id, list);
                    Item t =  new Item(p, quantity, p.getPrice());
                    addItem(t);
                }
            }
        } catch (NumberFormatException e){
            
        }
    }
    public List<String> getUserList(){
        List<String> list = new ArrayList<>();
        for (Item i : items) {
            if(!isAlreadyExist(i.getProduct().getUserID(), list)){
                list.add(i.getProduct().getUserID());
            }
        }
        return list;
    }
    private boolean isAlreadyExist(String userID, List<String> list){
        for (String i : list) {
            if(userID.equals(i)){
                return true;
            }
        }
        return false;
    }
    public int getTotalProduct(){
        int count = 0;
        for (Item i : items) {
            count += i.getQuantity();
        }
        return count;
    }
    public static void main(String[] args) {
        String txt ="1:1,2:1,22:10";
        ProductDAO d = new ProductDAO();
        Cart cart = new Cart(txt, d.getAll());
        List<Item> l1 = cart.getItems();
        for (int i = 0; i < l1.size(); i++) {
            System.out.println(l1.get(i).getProduct().getName());
        }
//        System.out.println(cart.isAlreadyExist("user123", cart.items));
        List<String> a = cart.getUserList();
        System.out.println(cart.items.size());
        System.out.println(a.size());
    }
}
