package com.saurav.InstagramBackendApp.repository;

import com.saurav.InstagramBackendApp.model.Admin;
import com.saurav.InstagramBackendApp.model.Like;
import com.saurav.InstagramBackendApp.model.Post;
import com.saurav.InstagramBackendApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILikeRepo extends JpaRepository<Like,Integer> {
    List<Like> findFirstByInstaPostAndLiker(Post instaPost, User liker);

    List<Like> findByInstaPost(Post post);
}
