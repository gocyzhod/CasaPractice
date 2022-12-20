package com.example.demo.Repository;

import com.example.demo.Entity.LoginUserEntity;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  LoginRepo extends JpaRepository<LoginUserEntity, Integer> {

    @Query(value = "select * from USERINFO" ,nativeQuery = true)
    public List<LoginUserEntity> findAllUser();




}
