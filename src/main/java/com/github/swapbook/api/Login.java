package com.github.swapbook.api;

import com.github.swapbook.model.LoginModel;
import com.github.swapbook.model.User;
import com.github.swapbook.repositories.users.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Date;

@RestController
public class Login {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/api/login")
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody LoginModel loginModel) {

        User loginUser = userRepository.getUserByEmail(loginModel.getEmail());
        if(loginUser == null) {
            return ResponseEntity.badRequest().body(null);
        }
        else {
            boolean isCorrect = loginUser.Verify(loginModel);
            if(isCorrect) {
                //response.addCookie((new Cookie("", "")));

                String jws = Jwts.builder()
                        .setIssuer("SwapBook")
                        .setSubject(loginUser.getEmail())
                        //.claim("name", loginUser.getEmail())
                        // Fri Jun 24 2016 15:33:42 GMT-0400 (EDT)
                        .setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
                        // Sat Jun 24 2116 15:33:42 GMT-0400 (EDT)
                        .setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
                        .signWith(
                                SignatureAlgorithm.HS256,
                                Keys.hmacShaKeyFor("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=".getBytes())
                        )
                        .compact();
                return ResponseEntity.ok().header("Authorization","Bearer "+jws).body(loginUser);
            }
            else
                return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/api/logout")
    public void get(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Authorization", "Empty");
    }
}
