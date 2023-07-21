package com.saurav.InstagramBackendApp.controller;

import com.saurav.InstagramBackendApp.model.*;
import com.saurav.InstagramBackendApp.service.AuthenticationTokenService;
import com.saurav.InstagramBackendApp.service.UserService;
import com.saurav.InstagramBackendApp.service.dto.SignInInput;
import com.saurav.InstagramBackendApp.service.dto.SignUpOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationTokenService authenticationTokenService;
    @PostMapping("/signUp")
    public SignUpOutput userSignUpOutput(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.signupUser(user);
    }
    @PostMapping("/signIn")
    public String userSignInInput(@RequestBody SignInInput signInInput) throws MessagingException, NoSuchAlgorithmException {
        return userService.signInUser(signInInput);
    }

    @DeleteMapping("/signOut/{userEmail}/{authToken}")
    public String userSignOut(@PathVariable String userEmail, @PathVariable String authToken){
        if(authenticationTokenService.authenticate(userEmail,authToken)) {
            return userService.signOutUser(userEmail);
        }
        return "PLease enter Valid Email And Authentication token value";
    }

    //upload content on instagram

    @PostMapping("/post")
    public String createInstaPost(@RequestBody Post post,@RequestParam String email, @RequestParam String authToken){
        if(authenticationTokenService.authenticate(email,authToken)) {
            return userService.createInstaPost(post,email);
        }
        return "Not Authenticated user activity !!!" +
                "please first authenticate yourself" +
                " than try to post";
    }

    @DeleteMapping("/post")
    public String removeInstaPost(@RequestParam Integer postID,@RequestParam String email, @RequestParam String authToken){
        if(authenticationTokenService.authenticate(email,authToken)) {
            return userService.removeInstaPost(postID,email);
        }
        return "Not Authenticated user activity !!!" +
                "please first authenticate yourself" +
                " than try to remove your post";
    }

    //commenting on a post

    @PostMapping("/comment")
    public String addCommentOnPost(@RequestParam String commenterEmail, @RequestParam String commenterAuthToken, @RequestBody Comment comment){
        if(authenticationTokenService.authenticate(commenterEmail,commenterAuthToken)) {
            return userService.addCommentOnPost(comment,commenterEmail);
        }
        return "Not Authenticated user activity !!!";
    }

    //delete comment
    @DeleteMapping("/comment/remove")
    public String removeCommentFromPost(@RequestParam Integer commentID,@RequestParam String email,@RequestParam String authToken){
        if(authenticationTokenService.authenticate(email,authToken)) {
            return userService.removeCommentFromPost(commentID,email);
        }
        return "Not Authenticated user activity !!!";
    }

    // like functionality

    @PostMapping("/like")
    public String addLike(@RequestBody Like like , @RequestParam String likerEmail, @RequestParam String likerToken){

        if(authenticationTokenService.authenticate(likerEmail,likerToken)) {
            return userService.addLike(like,likerEmail);
        }
        return "Not Authenticated user activity !!!";
    }
    @GetMapping("/likes/post/{postId}/count")
    public String getLikeCountByPost(@PathVariable Integer postId, @RequestParam  String userEmail,@RequestParam String userToken){

        if(authenticationTokenService.authenticate(userEmail,userToken)) {
            return userService.getLikeCountByPost(postId,userEmail);
        }
        return "Not Authenticated user activity !!!";
    }

    //remove like

    @DeleteMapping("like")
    public String removeLikeFromPost(@RequestParam Integer likerId,@RequestParam String likerEmail,@RequestParam String likerToken){

        if(authenticationTokenService.authenticate(likerEmail,likerToken)) {

            return userService.removeLikeFromPost(likerId,likerEmail);
        }
        return "Not Authenticated user activity !!!";
    }

    @PostMapping("/follow")
    public String followUser(@RequestBody Follow follow , @RequestParam String followerEmail, @RequestParam String followerToken){

        if(authenticationTokenService.authenticate(followerEmail,followerToken)) {

            return userService.followUser(follow,followerEmail);
        }
        return "Not Authenticated user activity !!!";
    }

    @DeleteMapping("/unFollow/target/{followId}")
    public String unFollowUser(@PathVariable Integer followId,@RequestParam String followerEmail,@RequestParam String flowerToken){

        if(authenticationTokenService.authenticate(followerEmail,flowerToken)) {
            return userService.unfollowUser(followId,followerEmail);
        }
        return "Not Authenticated user activity !!!";
    }

}