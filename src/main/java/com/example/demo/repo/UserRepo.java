package com.example.demo.repo;

import com.example.demo.enitity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepo extends JpaRepository <User,Long>{


    boolean existsByUserName(String userName);


    boolean existsByEmail(String email);

    User findByUserName(String userName);

    @Transactional
    @Modifying
    @Query("UPDATE User user SET user.email=:#{#user.email}," +
            "user.tel=:#{#user.tel}," +
            "user.password=:#{#user.password}"+
            " WHERE user.id =:#{#user.id}")
    int updateUserProfile(User user);

}
