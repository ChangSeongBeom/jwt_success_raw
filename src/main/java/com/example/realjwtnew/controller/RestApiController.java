package com.example.realjwtnew.controller;

import com.example.realjwtnew.model.User;
import com.example.realjwtnew.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestApiController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @PostMapping("home")
    public String home(){
        return "<h1>home</h1>";
    }

    @PostMapping("token")
    public String token(){
        return "<h1>token</h1>";
    }

    @PostMapping("join")
    public String join(@RequestBody User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        userRepository.save(user);
        return "회원가입 완료";
    }

    @GetMapping("/api/v1/user")
    public String user(){
        return "user";
    }

    //user,manager
    @GetMapping("/api/v1/manager")
    public String manager(){
        return "manager";
    }

    //admin
    @GetMapping("/api/v1/admin")
    public String admin(){
        return "admin";
    }
}
