package com.hrmanagement.hrmanagement.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hrmanagement.hrmanagement.model.*;;
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
