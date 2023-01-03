package com.example.realjwtnew.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.realjwtnew.config.auth.PrincipalDetails;
import com.example.realjwtnew.model.User;
import com.example.realjwtnew.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

//스프링 내부에 있는 필터
//login 요청해서 username,password 전송하면 (post)
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private final AuthenticationManager authenticationManager;


    // login 요청 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter : 로그인 시도중");

        try {
            // 1. username, password 받아서
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(), User.class);
            System.out.println(user.toString());

            // 1-2. 인증 토큰 생성
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

            // 2. 정상인지 로그인 시도 해봄. authenticationManager로 로그인 시도를 하면
            // PrincipalDetailsService가 호출 loadUserByUsername() 함수가 실행된 후 정상이면 authentication이 리턴됨.
            // authentication이 정상 리턴된다는 것은 -> DB에 있는 username과 password가 일치한다는 것.
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // 3. PrincipalDetails를 세션에 담고 (권한 관리 위해. 권한 1개뿐이라면 필요없음) => 로그인이 되었다는 뜻
//            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

//            System.out.println("ppppp"+principalDetails.getUser().getUsername()+""+principalDetails.getUser().getPassword());
//            //JWT토큰 만들기
//            System.out.println(principalDetails.getUser().getUsername());

            return authentication;

        }catch(IOException e) {
            e.printStackTrace();
        }

        // 4. JWT 토큰 만들어서 응답하면 됨

        return null;
    }
    //attemp가 다 끝났으면 successfulAuthentication함수 실행됨


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
        //H
        String jwtToken= JWT.create()
                .withSubject("test토큰")
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*10))//토큰 만료시간 10분
                .withClaim("id",principalDetails.getUser().getId())
                .withClaim("username",principalDetails.getUser().getUsername())
                .sign(Algorithm.HMAC512("test"));

        response.addHeader("Authrization","Bearer "+jwtToken);
    }


}