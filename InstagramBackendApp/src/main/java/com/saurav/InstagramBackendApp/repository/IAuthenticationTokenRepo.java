package com.saurav.InstagramBackendApp.repository;

import com.saurav.InstagramBackendApp.model.Admin;
import com.saurav.InstagramBackendApp.model.AuthenticationToken;
import com.saurav.InstagramBackendApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationTokenRepo extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findFirstByTokenValue(String authTokenValue);

    AuthenticationToken findFirstByUser(User user);
}
