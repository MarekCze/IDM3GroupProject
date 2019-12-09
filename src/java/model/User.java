/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements Serializable {

    private int user_id;
    private String full_name;  
    private String email;
    private String k_number;
    private int contact_number;
    private String profile_image;
    private String password;
    private String course_code;
    private int course_year;

    public User() {
    }

    public User(String full_name, String email, String k_number, int contact_number, String profile_image, String password, String course_code, int course_year) {

        this.full_name = full_name;
        this.email = email;
        this.k_number = k_number;
        this.contact_number = contact_number;
        this.profile_image = profile_image;
        this.password = password;
        this.course_code = course_code;
        this.course_year = course_year;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int user_id, String full_name, String email, String k_number, int contact_number, String profile_image, String password, String course_code, int course_year) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.email = email;
        this.k_number = k_number;
        this.contact_number = contact_number;
        this.profile_image = profile_image;
        this.password = password;
        this.course_code = course_code;
        this.course_year = course_year;
    }
    
    public int getUser_id() {
        return user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getK_number() {
        return k_number;
    }

    public int getContact_number() {
        return contact_number;
    }

    public void setContact_number(int contact_number) {
        this.contact_number = contact_number;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCourse_code() {
        return course_code;
    }

    public int getCourse_year() {
        return course_year;
    }

    public void setCourse_year(int course_year) {
        this.course_year = course_year;
    }


    public User Login(String email, String password) {

        Connection connection = DatabaseUtilityClass.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        String query = "Select * from User where email = ? AND password = ?";

        try {

            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            resultSet = ps.executeQuery();
          
           
            while (resultSet.next()) {
                this.user_id = resultSet.getInt("user_id");
                this.full_name = resultSet.getString("full_name");
                this.email = resultSet.getString("email");
                this.k_number = resultSet.getString("k_number");
                this.contact_number = resultSet.getInt("contact_number");  
                this.profile_image = resultSet.getString("profile_image");
                this.password = resultSet.getString("password");
                this.course_code = resultSet.getString("course_code");             
                this.course_year = resultSet.getInt("course_year");
                
            }

            connection.close();

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        return this;

    }
public User saveToDatabase() {

        Connection connection = DatabaseUtilityClass.getConnection();
         
        String sql = "INSERT INTO User (full_name,email,k_number,contact_number,profile_image,password,course_code,course_year) VALUES (?,?,?,?,?,?,?,?);";
        String query = "SELECT LAST_INSERT_ID()";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            PreparedStatement ps2 = connection.prepareStatement(query);
            ps.setString(1, this.getFull_name());
            ps.setString(2, this.getEmail());
            ps.setString(3, this.getK_number());
            ps.setInt(4, this.getContact_number());
            ps.setString(5, this.getProfile_image());
            ps.setString(6, this.getPassword());
            ps.setString(7, this.getCourse_code());
            ps.setInt(8, this.getCourse_year());

            ps.executeUpdate();
            ResultSet rs = ps2.executeQuery();
            while(rs.next()){
                this.user_id = rs.getInt(1);
            }
               
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return this;
    }
    public User updateUser() {

        Connection connection = DatabaseUtilityClass.getConnection();
         
        String sql = "UPDATE user SET full_name = ?, email = ?, "
                + "k_number = ?, contact_number = ?, profile_image = ?, "
                + "password = ?, course_code = ?, course_year = ? WHERE user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setString(1, this.getFull_name());
            ps.setString(2, this.getEmail());
            ps.setString(3, this.getK_number());
            ps.setInt(4, this.getContact_number());
            ps.setString(5, this.getProfile_image());
            ps.setString(6, this.getPassword());
            ps.setString(7, this.getCourse_code());
            ps.setInt(8, this.getCourse_year());
            ps.setInt(9, this.getUser_id());

            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return this;
    }

    

}
