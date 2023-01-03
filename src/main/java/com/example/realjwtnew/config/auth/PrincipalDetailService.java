package com.example.realjwtnew.config.auth;

import com.example.realjwtnew.model.User;
import com.example.realjwtnew.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Pincipal Detail Service의 loadUserbyUsername()");
        User userEntity=userRepository.findByUsername(username);
        System.out.println("USER ENTITY"+userEntity);
        return new PrincipalDetails(userEntity);
    }
}
