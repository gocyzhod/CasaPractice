package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Jwt.JwtTokenProvider;
import com.example.demo.Repository.JwtUserRepo;
import com.example.demo.Repository.LoginRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class JwtTestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtUserRepo userRepository;

    final String BIRTH = "001200";
    final String EMAIL = "aabbcc@gmail.com";
    final String NICKNAME = "침착맨";
    final Long SEQUENCEID = Long.valueOf(1);
    final String GENDER = "남";
    final String ADMIN = "일반회원";

    User user = User.builder()
            .userEmail(EMAIL)
            .userBirth(BIRTH)
            .userNickname(NICKNAME)
            .admin(ADMIN)
            .gender(GENDER)
            .userSequenceId(SEQUENCEID)
            .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
            .build();



    @PostMapping("/join")
    public String join(){
        logger.info("로그인 시도됨");


        userRepository.save(user);
        logger.info("### Success ###");

        return user.toString();

    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        log.info("user email = {}", user.get("email"));
        User member = userRepository.findByUserEmail(user.get("email"));
                //.orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }

    @PostMapping("/test")
    public String test(){

        return "<h1>test 통과</h1>";
    }

}
