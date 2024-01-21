package com.example.secpart3;

import com.example.secpart3.dao.User;
import com.example.secpart3.dao.UserRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SecPart3Application {
    @Autowired
    private UserRepo userRepo;

    public static void main(String[] args) {
        SpringApplication.run(SecPart3Application.class, args);

    }
  // @PostConstruct
    void initUsers(){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        userRepo.save(User.builder().id(1).username("test").password(passwordEncoder.encode("1234")).build());
        userRepo.save(User.builder().id(2).username("test2").password("1234").build());
    }
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
