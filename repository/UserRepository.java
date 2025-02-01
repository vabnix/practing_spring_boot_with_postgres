package com.vaibhav.spring.repository;

import com.vaibhav.spring.data.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData, String> {
}
