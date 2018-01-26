/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import db.Database;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Entita diskuzní komentář
 * @author Jirka_
 * 
 */
public class DiscussionComment extends Model {
   
    private int id;
    private int discussionThreadId;
    private int userId;
    private String text;
    private Timestamp createdAt;

    /**
     * Konstruktor pro komentář. Slouží pro vytvoření komentáře při selectu
     * z databáze
     * 
     * @param id
     * @param discussionThreadId
     * @param userId
     * @param text
     * @param createdAt
     */
    public DiscussionComment(int id, int discussionThreadId, int userId, String text, Timestamp createdAt) {
        this.id = id;
        this.discussionThreadId = discussionThreadId;
        this.userId = userId;
        this.text = text;
        this.createdAt = createdAt;
    }

    /**
     * Metoda pro vytvoření komentáře pokud ho teprve budeme vládat do databáze.
     * 
     * @param discussionThreadId
     * @param userId
     * @param text
     */
    public DiscussionComment(int discussionThreadId, int userId, String text) {
        this.discussionThreadId = discussionThreadId;
        this.userId = userId;
        this.text = text;
        
        Date date = new Date();
        
        this.createdAt = new Timestamp(date.getTime());
        
        ArrayList<String> paramList = new ArrayList<>();
        paramList.add(String.valueOf(this.discussionThreadId));
        paramList.add(String.valueOf(this.userId));
        paramList.add(this.text);
        paramList.add(this.createdAt.toString());
        
        Database
            .getInstance()
            .executeAction(
                    "insert into discussion_comment(discussion_thread_id, user_id, text, created_at)"
                    + "values(?, ?, ?, ?)" , paramList);
    }

    /**
     *  getter pro id
     * 
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * setter pro id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter pro id vlákna
     * @return thread id
     */
    public int getDiscussionThreadId() {
        return discussionThreadId;
    }

    /**
     * nastaví id vlákna
     * @param discussionThreadId
     */
    public void setDiscussionThreadId(int discussionThreadId) {
        this.discussionThreadId = discussionThreadId;
    }

    /**
     * getter pro user id
     * @return user id
     */
    public int getUserId() {
        return userId;
    }
    
    /**
     * getter pro autorovo jméno
     * @return autorovo jméno
     */
    public String getAuthorName() {
        return User.fetchUser(userId).getUsername();
    }
    
    /**
     * getter pro autora
     * @return autor
     */
    public User getAuthor() {
        return User.fetchUser(userId);
    }

    /**
     * setter pro id uživatele
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    
    
}
