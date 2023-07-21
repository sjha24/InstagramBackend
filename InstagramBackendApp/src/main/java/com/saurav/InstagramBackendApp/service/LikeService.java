package com.saurav.InstagramBackendApp.service;

import com.saurav.InstagramBackendApp.model.Like;
import com.saurav.InstagramBackendApp.model.Post;
import com.saurav.InstagramBackendApp.model.User;
import com.saurav.InstagramBackendApp.repository.IAdminRepo;
import com.saurav.InstagramBackendApp.repository.ILikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    @Autowired
    ILikeRepo likeRepo;

    public String addLike(Like like) {
        likeRepo.save(like);
        return "Insta Post Liked successfully";
    }

    public boolean isLikeAllowedOnThisPost(Post instaPost, User liker) {

        List<Like>likeList = likeRepo.findFirstByInstaPostAndLiker(instaPost,liker);

        return likeList.isEmpty();
    }

    public String getLikeCountByPost(Post post) {

        List<Like>likes = likeRepo.findByInstaPost(post);

        return String.valueOf(likes.size());
    }

    public Like findLikeById(Integer likerId) {

        return likeRepo.findById(likerId).orElse(null);
    }

    public void removeLike(Like like) {

        likeRepo.delete(like);
    }
}
