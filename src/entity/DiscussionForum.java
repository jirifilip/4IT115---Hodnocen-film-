/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 * Entita diskuzní fórum
 * @author Jirka_
 */
public class DiscussionForum extends Model {
    
    private ArrayList<DiscussionThread> discussionThreadsList =
            new ArrayList<DiscussionThread>();
    
    /**
     *  Bezparametrický konstruktor
     */
    public DiscussionForum() {
        discussionThreadsList = DiscussionThread.fetchAll();
        
    }

    /**
     * Metoda pro získání listu diskuzních vláken
     * @return seznam vláken
     */
    public ArrayList<DiscussionThread> getDiscussionThreadsList() {
        return DiscussionThread.fetchAll();
    }

    /**
     * nestaví seznam vláken
     * @param discussionThreadsList
     */
    public void setDiscussionThreadsList(
            ArrayList<DiscussionThread> discussionThreadsList) {
        this.discussionThreadsList = discussionThreadsList;
    }
    
    
    
}
