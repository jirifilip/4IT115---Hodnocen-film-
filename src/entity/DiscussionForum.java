/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author Jirka_
 */
public class DiscussionForum extends Model {
    
    private ArrayList<DiscussionThread> discussionThreadsList = new ArrayList<DiscussionThread>();
    
    public DiscussionForum() {
        discussionThreadsList = DiscussionThread.fetchAll();
        
        for (DiscussionThread thread : discussionThreadsList) {
            System.out.println(thread.getTitle());
        }
    }

    public ArrayList<DiscussionThread> getDiscussionThreadsList() {
        return discussionThreadsList;
    }

    public void setDiscussionThreadsList(ArrayList<DiscussionThread> discussionThreadsList) {
        this.discussionThreadsList = discussionThreadsList;
    }
    
    
    
}
