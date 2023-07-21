package com.saurav.InstagramBackendApp.repository;

import com.saurav.InstagramBackendApp.model.Admin;
import com.saurav.InstagramBackendApp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepo extends JpaRepository<Post,Integer> {
}
