# Instagram Backend System Application
[![Java](https://img.shields.io/badge/Java>=8.0-blue.svg)](https://docs.spring.io/spring-boot/docs/0.5.0.M6/api/org/springframework/boot/SpringApplication.html)
[![maven](https://img.shields.io/badge/maven->=3.0.5-green.svg)](https://www.npmjs.com/package/npm/v/5.5.0)
[![springBoot](https://img.shields.io/badge/SpringBoot->=3.0.6-blue.svg)](https://nodejs.org/en/blog/release/v9.3.0)
>This project is a basic web application that allows users to sign up, SignIn , Sign Out and manage their profile information. Additionally, users can create posts and can 
like and comment other post also view posts created by other users. The application uses authentication tokens to secure user data and ensure that only authenticated users can access certain features of the application.

[Homepage]();

## Framework used
 * Spring Boot
## Languaged Uesd
 * Java
## Dependencies
 * Spring Web
 * Spring boot Devtool
 * lombok
 * MySQL
 * Validation
 * swagger

## Data Model
>The data model is defined in the Hotel Room Model class, which has the following attributes
```
* Admin
adminID (Integer) : Unique identifier for Admin ID and Generation Type AUTO.
adminName (String) : Admin Name
adminEmail (String) : Admin Email.
adminPassword (String) : Admin Password.
* User
Id (integer) : Unique identifier for Admin ID and Generation Type AUTO.
userName (string) : User Full Name.
UserHandle (string) : User Handle name.
userBio (string) : User Bio.
userEmail (string) : User Email.
userPassword (string) : User Password.
userGernder (Enum-String) : User Gender Male,Female and Other.
AccountType (Enum-String) : User Account type public or Private.
isBlueTicked (boolean) : user Have blueTicked or Note.

* Post

postID (Integer) : Unique identifier for Patient Id Generation Type AUTO.
postContent (String) : Post Content.
postCaption (String) : Post Caption.
postLocation (String) : Post Location.
postType (Enum-string) : IMAGE,VIDEO,REEL.
postCreatedTimeStamp (LocalDateTime) : Post creation date and time.

* Like

likeID (Integer) : Unique identifier for Patient Id Generation Type AUTO.
instaPost (post) : Post Details.
liker (user) : user Details who is like.

* Comment

commentID (Integer) : Unique identifier for Patient Id Generation Type AUTO.
commentBody (String) : comment by User.
commentCreationTimeStamp (LocalDateTime) : User comment Date Time.
instaPost (post) : user comment on certain post.
commenter (user) : User who is comment on post.

* Follow

followId (Integer) :  Unique identifier for Patient Id Generation Type AUTO.
currentUser (User) : Parrent User.
currentUserFollower (User) : Parrent user follow other user.

* AuthenticationToken

tokenId (Long) :  Unique identifier for Patient Id Generation Type AUTO.
tokenValue (String) : Token value genrated By UUID in Random form.
tokenCreationDateTime (LocalDateTime) : Token Creation Date Time.
```
## Validation
```
To validate the input we get from client, we've used validation annotations that are used to enforce specific constraints on the values of the variables. These constraints ensure that the data input by the user is of the correct format and meets certain criteria.
* @Valid - In Spring Framework, the @Valid annotation is used in conjunction with @PostMapping and @PutMapping annotations, which are used to handle POST and PUT requests, respectively. When used with @PostMapping or @PutMapping, the @Valid annotation is typically applied to a method parameter annotated with @RequestBody.
* @NotEmpty: This annotation is used to validate that the annotated field is not null or empty.
* @NotNull: This annotation is used to validate that the annotated field is not null.
* @Max : This annotation is used to validate that the annotated field is not greater than the mentioned value in field.
* @Min : This annotation is used to validate that the annotated field is not smaller than the mentioned value in field.
```
## Data Flow
```
1. User send as a request to the application throgh the endpoints
2. the api recived request and sends it to the appropriate controll method
3. the controller method makes a call to the method in service class.
4. he controller method returns a response to the API
5. The API sends the response back to the user
```
## Api End Points
The following endpoints are available in the API:
* User Controller
```
POST /user/signup: Creates a new user account.
POST /user/signin: Authenticates a user and generates an access token .
DELETE/user/signOut/{userEmail}/{authToken} : user cant sign out account .
POST /user/post : Creates a new post.
Delete/user/post : User can delete post by post ID.
Post/user/comment : User can comment on post.
Delete/user/comment/remove : User can delete their post.
post/user/like : user can like post.
post/user/like/post/{postId}/count : User can get all like certain post by post id.
Delete/user/like : User can remove their like from post.
Post/user/follow : User can follow other user.
Post/user/unFollow/target/{followId) : User can unfollow also.

```
## Data Structure Used
* SQL database
```
We have used Persistent database to implement CRUD Operations.
```

## Project Summary
```
The project is a basic web application built using Java and the Spring framework. It allows users to sign up, sign in,sign Out and manage their profile information. Users can also create psot ,view posts, Like post , Dislike post , Comment on post , Delete their comment , follow other user and also Unfollow . The application uses authentication tokens to secure user data and ensure that only authenticated users can access certain features. The API endpoints include user signup, signin,sign Out and update details, post creation and retrieval, and authentication token creation.
```
## Author

Saurav Kumar

* twiter : [@saurav](https://twitter.com/Sauravjha24)
* Github : [@sjha](https://github.com/sjha24)

## Contributing

Contributions , issues and features requestes are welcome !

Feel free to check issues page

## Show your support

Give a rating if this project help you

## License

Copyright : 2023 [Saurav Kumar]()
This project is [GeekSter](https://www.geekster.in/) license
