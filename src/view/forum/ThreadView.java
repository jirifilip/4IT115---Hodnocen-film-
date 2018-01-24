/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.forum;

import controller.MainController;
import entity.DiscussionComment;
import entity.DiscussionThread;
import java.util.ArrayList;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jirka_
 */
public class ThreadView extends ScrollPane {
 
    private ArrayList<DiscussionComment> commentList = new ArrayList<>();
    private DiscussionThread discussionThread;
    private MainController controller;
    private TextArea newCommentTextArea;
    private Button addComment;
    
    
    public ThreadView(MainController controller) {
        this.controller = controller;
    }
    
    public void init(DiscussionThread thread) {
        
        this.setContent(null);
        
        

        setFitToWidth(true);

        
        this.discussionThread = thread;
        
        commentList = discussionThread.fetchAllComments(false);
        
        GridPane commentBox = new GridPane();
        
        commentBox.setAlignment(Pos.TOP_CENTER);
        commentBox.setVgap(10);
        commentBox.setHgap(10);
        commentBox.setPadding(new Insets(25, 25, 25, 25));
        
        int i = 0;
        for (DiscussionComment comment : commentList) {
            Label nameLabel = new Label(comment.getAuthorName());
            TextArea textArea = new TextArea();
            textArea.setText(comment.getText());
            textArea.setEditable(false);
            textArea.setMaxHeight(50);
            textArea.setMaxWidth(250);
            
            String dateString = new Date(comment.getCreatedAt().getTime()).toLocaleString();
            
            commentBox.add(new Label(dateString), 0, i);
            commentBox.add(nameLabel, 1, i);
            commentBox.add(textArea, 2, i);
            
            i++;
        };
        
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        
        commentBox.add(separator, 0, i, 3, 1);
        i++;
        
        newCommentTextArea = new TextArea();
        addComment = new Button("Přidat komentář");
        
        addComment.setOnAction(e -> {
            
            new DiscussionComment(thread.getId(), controller.getCurrentUser().getId(), newCommentTextArea.getText());
        
            controller.getMainAppView().onForumButtonClick(new ActionEvent());
        
            controller.alert("Informace", "Komentář byl přidán!", "");
        });
        
        commentBox.add(newCommentTextArea, 0, i, 3, 1);
        commentBox.add(addComment, 1, i + 1, 3, 1);
        
       this.setContent(commentBox);
       
       prepare();
    }
    
    public void prepare() {
        if (controller.getCurrentUser() == null) {
            newCommentTextArea.setVisible(false);
            addComment.setVisible(false);
        }
    }
    
}
