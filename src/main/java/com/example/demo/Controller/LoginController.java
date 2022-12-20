package com.example.demo.Controller;

import com.example.demo.Entity.LoginUserEntity;
import com.example.demo.Repository.LoginRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequiredArgsConstructor
@RequestMapping(value = "/login")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());


    @Autowired
    private LoginRepo loginRepo;



    @GetMapping(value = "/first")
    public ResponseEntity<Object> accessCasa(@RequestBody LoginUserEntity LoginUserEntity) throws Exception{

        logger.info(String.valueOf(LoginUserEntity));
        List<LoginUserEntity> user = new ArrayList<LoginUserEntity>();
        user = loginRepo.findAllUser();
        logger.info(String.valueOf(user));

        // default
        int checkPoint = 0 ;

        for ( int i = 0 ; i < user.size() ; i ++ ){
            //if (user.get(i).getUid() == LoginUserEntity.getUid()){
                if ( LoginUserEntity.toString().equals(user.get(i).toString()) ) {
                    checkPoint = 1;
                }
            //}
        }
        if ( checkPoint == 0) {
            return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
