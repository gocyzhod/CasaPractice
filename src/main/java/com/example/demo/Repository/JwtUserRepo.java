package com.example.demo.Repository;

import com.example.demo.Entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface JwtUserRepo extends JpaRepository<User,Long> {

    @Query(value = "select * from tuserinfo where USER_EMAIL = :USER_EMAIL" ,nativeQuery = true)
    //public List<LoginUserEntity> findByUserEmail(String email);
    public User findByUserEmail(@Param("USER_EMAIL") String USER_EMAIL);

}
