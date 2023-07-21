package com.saurav.InstagramBackendApp.repository;

import com.saurav.InstagramBackendApp.model.Admin;
import com.saurav.InstagramBackendApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Integer> {
    User findFirstByUserEmail(String newEmail);
}
