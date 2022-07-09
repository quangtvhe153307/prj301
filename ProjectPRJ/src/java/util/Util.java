/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Admin
 */
public class Util {
    public static boolean isValidPhoneNumber(String phone){
        if(phone.length() != 10){
            return false;
        }
        for (int i = 0; i < phone.length(); i++) {
            if(!Character.isDigit(phone.charAt(i))){
               return false;
            }
        }
        if(!(phone.charAt(0)+"").equals("0")){
            return false;
        }
        return true;
    }
    public static boolean isValidName(String name){
        if(name.length() > 26 || name.length() < 2){
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            if(!(Character.isLetter(name.charAt(i)) || Character.isSpaceChar(name.charAt(i)))){
                return false;
            }
        }
        return true;
    }
    public static boolean isValidCity(String city){
        if(city.length() < 3 || city.length() > 10){
            return false;
        }
        for (int i = 0; i < city.length(); i++) {
            if(!(Character.isLetter(city.charAt(i)) || Character.isSpaceChar(city.charAt(i)))){
                return false;
            }
        }
        return true;
    }
    public static boolean isValidAddress(String address){
        if(address.length() > 100){
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(isValidName("hhhhhhhh3hhhh hhhhhhhhhhhh"));
    }
}
