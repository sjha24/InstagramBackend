package com.saurav.InstagramBackendApp.repository;

import com.saurav.InstagramBackendApp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepo extends JpaRepository<Admin,Integer> {
}
