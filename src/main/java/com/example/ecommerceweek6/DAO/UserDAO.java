package com.example.ecommerceweek6.DAO;

import com.example.ecommerceweek6.CONNECTION.Connections;
import com.example.ecommerceweek6.Exceptions.customExceptions;
import com.example.ecommerceweek6.Models.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    static Connection dB = Connections.getConnection();

    public static ResultSet getAllUsers() throws SQLException {
        String query = "SELECT * FROM users";
        Statement statement = dB.createStatement();
        return statement.executeQuery(query);
    }


    public static List<String> getUser() throws SQLException {
        ResultSet checkers = getAllUsers();
        List<String> usernames = new ArrayList<>();
        while(checkers.next()){
            usernames.add(checkers.getString("username"));
        }
        return usernames;
    }
    public boolean registerUser(Users user) throws SQLException {
        boolean isRegistered;
        String query = "insert into users (name, username, email, phone_number, pass_word) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = dB.prepareStatement(query);
            stmt.setString(1, user.getFirstname());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getPassword());
        if(!getUser().contains(user.getUsername())) {
            stmt.executeUpdate();
            isRegistered = true;
            stmt.close();
        }else{
            isRegistered = false;
        }
        return isRegistered;
    }


    public boolean checkUser(String userName, String passWord) throws SQLException {
        boolean isTrue = false;
        String query = "SELECT user_id FROM users WHERE username = ? AND pass_word = ?";
        PreparedStatement stmt = dB.prepareStatement(query);
        stmt.setString(1, userName);
        stmt.setString(2, passWord);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            isTrue = true;
        }
        stmt.close();
        return isTrue;
    }

    public boolean forAdmin(String userName){
        boolean isAdmin = false;
        if(userName.equals("admin")){
            isAdmin = true;
        }
        return isAdmin;
    }

    public static void main(String[] args) throws SQLException {
        UserDAO dao = new UserDAO();
        Users user = new Users("Nte", "Chinedum102", "chinedum@nte.com", "08068686868", "877566665");
        boolean xx = dao.registerUser(user);
        System.out.println(xx);
//        List<String> users = UserDAO.getUser();
//        for(String x : users){
//            System.out.println(x);
//        }
    }
}
