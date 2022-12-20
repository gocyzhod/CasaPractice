package com.example.demo.Config;


import com.example.demo.Jwt.JwtAuthenticationFilter;
import com.example.demo.Jwt.JwtTokenProvider;
import com.example.demo.Service.UserDetailsService;
import com.example.demo.Servicepl.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//@Configuration(proxyBeanMethods = false)
//@ConditionalOnDefaultWebSecurity
//@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
//@RequiredArgsConstructor
//@EnableWebSecurity


@EnableWebSecurity
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
public class SecurityConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());


    private final JwtTokenProvider jwtTokenProvider;
    //private final CustomOAuth2UserService customOAuth2UserService;
    private final UserDetailsService service;


    //20221220
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        logger.info("##### Access filterChain #####");


        http.csrf().disable();
        http.formLogin().disable();
        http.httpBasic().disable()
                .authorizeHttpRequests()

                .requestMatchers("/admin/**").hasRole("ROLE_ADMIN")
                .requestMatchers("/user/**", "/test").hasAuthority("ROLE_USER")
                //.requestMatchers("/test/getMapping", "/login","/join").permitAll()
                .requestMatchers("/test/getMapping", "/login","/join","/**").permitAll()
                //.anyRequest().authenticated()
                .and()
                // security 전에 jwt 토큰 검사가 진행된다.
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);
        //        .httpBasic(withDefaults());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();

    }

//    @Bean
//    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
//        http
//                .securityMatcher("/**")
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().hasRole("USER")
//                )
//                .httpBasic(withDefaults());
//        return http.build();
//    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web) -> web.ignoring().requestMatchers( "/first");
    }


}
