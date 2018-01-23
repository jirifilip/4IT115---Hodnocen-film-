/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import db.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jirka_
 */
public class DiscussionThread extends Model {
    
    private int id;
    private String title;
    private Timestamp createdAt;
    
    private ArrayList<DiscussionComment> discussionCommentsList = new ArrayList<DiscussionComment>();

    public DiscussionThread(int id, String title, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        
        fetchAllComments();
        
        for (DiscussionComment comment : discussionCommentsList) {
            System.out.println(comment.getText());
        }
    }

    public DiscussionThread(String title) {
        this.title = title;
        
        Date date = new Date();
        
        this.createdAt = new Timestamp(date.getTime());
        
        ArrayList<String> paramList = new ArrayList<>();
        paramList.add(String.valueOf(this.title));
        paramList.add(String.valueOf(this.createdAt.toString()));
        
        Database
            .getInstance()
            .executeAction(
                    "insert into discussion_thread(title, created_at)"
                    + "values(?, ?)" , paramList);
    }
    
    
    private void fetchAllComments() {
        if (!discussionCommentsList.isEmpty()) {
            return;
        }
        
        try (Connection conn = Database.getConnection()){
            PreparedStatement statement = null;
            ResultSet rs = null;
            
            statement = conn.prepareStatement(
                    "select * from discussion_comment where discussion_thread_id = ?");
            
            statement.setString(1, String.valueOf(this.id));
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt(1);
                int discussionThreadId = rs.getInt(2);
                int userId = rs.getInt(3);
                String text = rs.getString(4);
                Timestamp createdAt = rs.getTimestamp(5);
                
                
                discussionCommentsList.add(
                    new DiscussionComment(id, discussionThreadId, userId, text, createdAt));
            }
            
            statement.close();
            conn.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<DiscussionThread> fetchAll() {
        ArrayList<DiscussionThread> threads = new ArrayList<>();
        
        try (Connection conn = Database.getConnection()){
            PreparedStatement statement = null;
            ResultSet rs = null;
            
            statement = conn.prepareStatement("select * from discussion_thread");
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                Timestamp createdAt = rs.getTimestamp(3);
                
                threads.add(new DiscussionThread(id, title, createdAt));
            }
            
            statement.close();
            conn.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return threads;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    
    
}
