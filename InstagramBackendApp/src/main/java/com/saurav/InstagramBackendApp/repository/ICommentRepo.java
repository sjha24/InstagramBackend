package com.saurav.InstagramBackendApp.repository;

import com.saurav.InstagramBackendApp.model.Admin;
import com.saurav.InstagramBackendApp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepo extends JpaRepository<Comment,Integer> {
}
