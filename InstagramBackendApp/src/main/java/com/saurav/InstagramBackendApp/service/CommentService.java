package com.saurav.InstagramBackendApp.service;

import com.saurav.InstagramBackendApp.model.Comment;
import com.saurav.InstagramBackendApp.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {
    @Autowired
    ICommentRepo commentRepo;

    public String addCommentOnPost(Comment comment) {
        comment.setCommentCreationTimeStamp(LocalDateTime.now());
        commentRepo.save(comment);
        return "Comment has been added";
    }

    public Comment findCommentById(Integer commentID) {
        return commentRepo.findById(commentID).orElse(null);
    }

    public void removeComment(Comment comment) {
        commentRepo.delete(comment);
    }
}
