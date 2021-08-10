package com.example.demo.repo;

import com.example.demo.enitity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository <User,Long>{


    boolean existsByUserName(String userName);


    boolean existsByEmail(String email);

    User findByUserName(String userName);

}
