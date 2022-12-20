package com.example.demo.Servicepl;

import com.example.demo.Repository.JwtUserRepo;
//import com.example.demo.Repository.LoginRepo;
import com.example.demo.Service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CustomUserDetailService implements UserDetailsService {


    private final JwtUserRepo userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ( userRepository.findByUserEmail(username) != null ){
            return userRepository.findByUserEmail(username);
        }
        else{
            return null;
        }

//        return userRepository.findByUserEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));


    }

}
