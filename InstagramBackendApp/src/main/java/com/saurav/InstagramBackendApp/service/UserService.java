package com.saurav.InstagramBackendApp.service;

import com.saurav.InstagramBackendApp.model.*;
import com.saurav.InstagramBackendApp.repository.IAdminRepo;
import com.saurav.InstagramBackendApp.repository.IAuthenticationTokenRepo;
import com.saurav.InstagramBackendApp.repository.IUserRepo;
import com.saurav.InstagramBackendApp.service.dto.SignInInput;
import com.saurav.InstagramBackendApp.service.dto.SignUpOutput;
import com.saurav.InstagramBackendApp.service.utility.emailUtility.MailHandler;
import com.saurav.InstagramBackendApp.service.utility.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;
    @Autowired
    IAuthenticationTokenRepo authenticationTokenRepo;
    @Autowired
    PostService postService;
    @Autowired
    CommentService commentService;
    @Autowired
    LikeService likeService;
    @Autowired
    FollowService followService;

    public SignUpOutput signupUser(User user) throws NoSuchAlgorithmException {
        boolean signupStatus = true;
        String signupStatusMessage = null;
        boolean isExistEmail = false;
        String newEmail = user.getUserEmail();
        //check if the User Email already exist :-
        if(newEmail == null) {
            signupStatus = false;
            signupStatusMessage = "Invalid Email";
            return new SignUpOutput(false, signupStatusMessage);
        }
        // check if this User email already exist or not
        User existingUser = userRepo.findFirstByUserEmail(newEmail);
        if(existingUser != null) {
            signupStatusMessage = "Email already registered !!!";
            signupStatus = false;
            return new SignUpOutput(false, signupStatusMessage);
        }
        //hash the password or encrypt the password
        String encryptPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());
        //save the User with the new encrypted password
        user.setUserPassword(encryptPassword);
        userRepo.save(user);
        signupStatusMessage = "User Registered Successfully";
        return new SignUpOutput(true,signupStatusMessage);
    }
    public String signInUser(SignInInput signInInput) throws NoSuchAlgorithmException, MessagingException {
        boolean signInStatus = true;
        String signInStatusMessage = null;
        String signInEmail = signInInput.getEmail();
        if(signInEmail == null){
            signInStatus = false;
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;
        }
        User existingUser = userRepo.findFirstByUserEmail(signInEmail);
        if(existingUser == null) {
            signInStatusMessage = "Email Not registered !!!";
            signInStatus = false;
            return signInStatusMessage;
        }
        //match password--->
        String encryptPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
        if(existingUser.getUserPassword().equals(encryptPassword)){

            //session should be created since password match and user email is valid

            AuthenticationToken authenticationToken = new AuthenticationToken(existingUser);
            authenticationTokenRepo.save(authenticationToken);
            MailHandler.sendMail(existingUser.getUserEmail(),"Email Testing",authenticationToken.getTokenValue());
            return "Token sent to your email";
//            return authenticationToken.getTokenValue();
        }else{
            signInStatusMessage = "Invalid email or password";
            return signInStatusMessage;
        }

    }
    public String signOutUser(String userEmail) {
        User user = userRepo.findFirstByUserEmail(userEmail);
        if(user.getUserEmail().equals(userEmail)) {
            AuthenticationToken authenticationToken = authenticationTokenRepo.findFirstByUser(user);
            authenticationTokenRepo.delete(authenticationToken);
            return "User sign out successfully";
        }
        return "User sign out cant be done";
    }

    public String createInstaPost(Post post,String email) {
        User postOwner = userRepo.findFirstByUserEmail(email);
        post.setPostOwner(postOwner);
        return postService.createInstaPost(post);
    }

    public String removeInstaPost(Integer postID,String email) {
        User user = userRepo.findFirstByUserEmail(email);
        return postService.removeInstaPost(postID,user);
    }

    public String addCommentOnPost(Comment comment,String commenterEmail) {

        boolean isValidPost = postService.isValidPost(comment.getInstaPost());
        if(isValidPost) {
            User commenter = userRepo.findFirstByUserEmail(commenterEmail);
            comment.setCommenter(commenter);
            return commentService.addCommentOnPost(comment);
        }
        return "Cannot Comment on Invalid post";
    }

    boolean authorisedCommentRemover(String email, Comment comment){

        String commentOwnerEmail = comment.getCommenter().getUserEmail();
        String  postOwnerEmail = comment.getInstaPost().getPostOwner().getUserEmail();
        return (commentOwnerEmail.equals(email) || postOwnerEmail.equals(email));
    }

    public String removeCommentFromPost(Integer commentID, String email) {

        Comment comment = commentService.findCommentById(commentID);

        if(comment != null){

            if(authorisedCommentRemover(email,comment)){
                commentService.removeComment(comment);
                return "Comment delete successfull";
            }else{
                return "Unauthorised activity detected !! comment delete do not allow";
            }

        }else{

            return "Invalid Request";
        }
    }

    public String addLike(Like like, String likerEmail) {

        Post instaPost = like.getInstaPost();
        boolean isValidPost = postService.isValidPost(instaPost);

        if(isValidPost) {
            User liker = userRepo.findFirstByUserEmail(likerEmail);

            if(likeService.isLikeAllowedOnThisPost(instaPost,liker)){
                like.setLiker(liker);
                return likeService.addLike(like);
            }else{
                return "Already Liked This Post";
            }

        }else {
            return "Can't like invalid post";
        }
    }

    public String getLikeCountByPost(Integer postID,String userEmail) {
        Post post = postService.getPostById(postID);

        if(post != null){
            return likeService.getLikeCountByPost(post);
        }else {
            return "Invalid post";
        }
    }

    private boolean authorisedLikeRemover(String potentialLikeRemover, Like like) {

        String likeOwnerEmail = like.getLiker().getUserEmail();

        return likeOwnerEmail.equals(potentialLikeRemover);
    }
    public String removeLikeFromPost(Integer likerId, String likerEmail) {

        Like like = likeService.findLikeById(likerId);

        if(like != null){

            if(authorisedLikeRemover(likerEmail,like)){

                likeService.removeLike(like);

                return "Like Remove successfull";

            }else{

                return "Unauthorised activity detected !! Like delete not allow";
            }

        }else{

            return "Invalid Request";
        }
    }

    public String followUser(Follow follow, String followerEmail) {

        User followTargetUser = userRepo.findById(follow.getCurrentUser().getUserId()).orElse(null);

        if(followTargetUser != null){
            User follower = userRepo.findFirstByUserEmail(followerEmail);

            if(followService.isFollowAllowed(followTargetUser,follower)) {

                followService.startFollowing(follow, follower);
                return follower.getUserHandle() + " is now following " + followTargetUser.getUserHandle();

            }else{
                return follower.getUserHandle() + " is Already following " + followTargetUser.getUserHandle();
            }
        }else{
            return "User to be followed Invalid";
        }
    }

    private boolean authorisedUnFollower(String email, Follow follow) {

        String targetEmail = follow.getCurrentUser().getUserEmail();

        String  followerEmail = follow.getCurrentUserFollower().getUserEmail();

        return (targetEmail.equals(email) || followerEmail.equals(email));
    }
    public String unfollowUser(Integer followId, String followerEmail) {

        Follow follow = followService.findFollowById(followId);

        if(follow != null){

            if(authorisedUnFollower(followerEmail,follow)){

                followService.unFlowUser(follow);
                return "Unfollowing Success";

            }else{
                return "Unauthorised activity detected !! Unfollow Not allow";
            }

        }else{

            return "Invalid Request";
        }
    }

}
