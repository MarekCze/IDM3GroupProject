/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Elizabeth.Bourke
 */
public class Notices {

    private int NoticeId;
    private String image;
    private String shortDescription;
    private String longDescription;
    private int userId;

    public Notices(String image, String shortDescription, String longDescription, int userId) {
        this.image = image;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.userId = userId;
    }

    public Notices() {
    }

    public Notices(int NoticeId, String image, String shortDescription, String longDescription, int userId) {
        this.NoticeId = NoticeId;
        this.image = image;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.userId = userId;
    }

    /**
     * Get the value of userId
     *
     * @return the value of userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Set the value of userId
     *
     * @param userId new value of userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Get the value of shortDescription
     *
     * @return the value of shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Set the value of shortDescription
     *
     * @param shortDescription new value of shortDescription
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Get the value of longDescription
     *
     * @return the value of longDescription
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * Set the value of longDescription
     *
     * @param longDescription new value of longDescription
     */
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    /**
     * Get the value of image
     *
     * @return the value of image
     */
    public String getImage() {
        return image;
    }

    /**
     * Set the value of image
     *
     * @param image new value of image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Get the value of NoticeId
     *
     * @return the value of NoticeId
     */
    public int getNoticeId() {
        return NoticeId;
    }

    /**
     * Set the value of NoticeId
     *
     * @param NoticeId new value of NoticeId
     */
    public void setNoticeId(int NoticeId) {
        this.NoticeId = NoticeId;
    }

    public ArrayList<Notices> getAllNotices() {

        ArrayList allnotices = new ArrayList<>();

        Connection connection = DatabaseUtilityClass.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        String query = "Select * from Notices";

        try {

            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Notices n = new Notices();
                n.setNoticeId(resultSet.getInt("NoticeId"));
                n.setImage(resultSet.getString("image"));
                n.setShortDescription(resultSet.getString("ShortDescription"));
                n.setLongDescription(resultSet.getString("longDescription"));
                n.setUserId(resultSet.getInt("UserId"));
                allnotices.add(n);
            }

            connection.close();
            

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        

        return allnotices;
    }
    
    public ArrayList<Notices> getUserNotices(int userId) {

        ArrayList userNotices = new ArrayList<>();

        Connection connection = DatabaseUtilityClass.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        String query = "Select * from notices WHERE userId = ?";

        try {
            
            ps = connection.prepareStatement(query);
            ps.setInt(1, userId);            
            resultSet = ps.executeQuery();
            System.out.println("dtrfcyvgbuyhniujm" + this.getUserId());
            while (resultSet.next()) {
                Notices n = new Notices();
                n.setNoticeId(resultSet.getInt("NoticeId"));
                n.setImage(resultSet.getString("image"));
                n.setShortDescription(resultSet.getString("ShortDescription"));
                n.setLongDescription(resultSet.getString("longDescription"));
                n.setUserId(userId);
                userNotices.add(n);
            }

            connection.close();
            

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        

        return userNotices;
    }
    
    public Notices saveToDB(){
        Connection connection = DatabaseUtilityClass.getConnection();
        
        String sql = "INSERT INTO notices (image,shortDescription,longDescription,userId) VALUES (?,?,?,?);";
        String query = "SELECT LAST_INSERT_ID()";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            PreparedStatement ps2 = connection.prepareStatement(query);
            ps.setString(1, this.getImage());
            ps.setString(2, this.getShortDescription());
            ps.setString(3, this.getLongDescription());
            ps.setInt(4, this.getUserId());

            ps.executeUpdate();
            ResultSet rs = ps2.executeQuery();
            while(rs.next()){
                this.NoticeId = rs.getInt(1);
            }
               
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            
        }
        return this;
    }

}
