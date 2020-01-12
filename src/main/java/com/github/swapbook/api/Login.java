package com.github.swapbook.api;

import com.github.swapbook.configuration.SecurityConstants;
import com.github.swapbook.model.LoginModel;
import com.github.swapbook.model.User;
import com.github.swapbook.repositories.users.UserDBRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Date;

@RestController
public class Login {
    @Autowired
    UserDBRepository userRepository;

    @PostMapping("/api/login")
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody LoginModel loginModel, HttpServletResponse response) {

        User loginUser = userRepository.getUserByEmail(loginModel.getEmail());
        if(loginUser == null) {
            return ResponseEntity.badRequest().body(null);
        }
        else {
            boolean isCorrect = loginUser.Verify(loginModel);
            if(isCorrect) {
                String jws = Jwts.builder()
                        .setIssuer("SwapBook")
                        .setSubject(loginUser.getEmail())
                        .setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
                        .setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
                        .signWith(
                                SignatureAlgorithm.HS256,
                                Keys.hmacShaKeyFor(SecurityConstants.JWT_SECRET.getBytes())
                        )
                        .compact();

                Cookie cookie = new Cookie(SecurityConstants.TOKEN_HEADER,jws);
                cookie.setMaxAge(3600);
                cookie.setPath("/");
                response.addCookie(cookie);
                
                return ResponseEntity.ok().body(loginUser);
            }
            else
                return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/api/logout")
    public void get(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader(SecurityConstants.TOKEN_HEADER, "Empty");
    }
}
