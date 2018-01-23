/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.MainController;
import entity.DiscussionComment;
import entity.DiscussionThread;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jirka_
 */
public class ThreadView extends ScrollPane {
 
    private ArrayList<DiscussionComment> commentList = new ArrayList<>();
    private DiscussionThread discussionThread;
    private MainController controller;
    
    
    public ThreadView(MainController controller) {
        this.controller = controller;
    }
    
    public void init(DiscussionThread thread) {
        
        this.getChildren().clear();
        
        this.discussionThread = thread;
        
        commentList = discussionThread.fetchAllComments();
        
        VBox threadVBox = new VBox();
        
        commentList.forEach(comment -> {
            VBox commentContainer = new VBox();
            
            Label nameLabel = new Label(comment.getAuthorName());
            TextArea textArea = new TextArea();
            textArea.setText(comment.getText());
            textArea.setEditable(false);
            textArea.setMaxHeight(100);
            
            commentContainer.getChildren().addAll(nameLabel, textArea);
            
            threadVBox.getChildren().add(commentContainer);
        });
        
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        
        
        VBox newCommentArea = new VBox();
        TextArea newCommentTextArea = new TextArea();
        Button addComment = new Button("Přidat komentář");
        
        newCommentArea.getChildren().addAll(newCommentTextArea, addComment);
        
        addComment.setOnAction(e -> {
            System.out.println("Komentář!");
            
            new DiscussionComment(thread.getId(), controller.getCurrentUser().getId(), newCommentTextArea.getText());
        
            controller.getMainAppView().onForumButtonClick(new ActionEvent());
        
        });
        
        
        this.setContent(new VBox(threadVBox, separator, newCommentArea));
    }
    
}
