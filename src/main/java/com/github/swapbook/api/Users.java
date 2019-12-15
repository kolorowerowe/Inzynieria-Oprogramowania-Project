package com.github.swapbook.api;

import com.github.swapbook.configuration.SecurityConstants;
import io.jsonwebtoken.Jwts;
import com.github.swapbook.model.User;
import com.github.swapbook.repositories.users.UserDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class Users {
    @Autowired
    UserDBRepository userRepository;

    @GetMapping("/api/users/all")
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userRepository.getUsers());
    }

    @GetMapping("/api/users/{id}")
    @ResponseBody
    public ResponseEntity<User> getUserById(HttpServletRequest request, @PathVariable(value = "id") int userId) {
        if(VerifyToken(request, userId))
            return ResponseEntity.ok().body(userRepository.getUserById(userId));
        else
            return  ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/api/users/put")
    public void createUser(@RequestBody User user) {
        userRepository.addToList(user);
    }

    @DeleteMapping("/api/users/{id}")
    public void deleteUser(HttpServletRequest request, @PathVariable(value = "id") int userId) {
        if(VerifyToken(request, userId))
            userRepository.deleteUserById((userId));
    }

    private boolean VerifyToken(HttpServletRequest request, int userId)
    {
        User user = userRepository.getUserById(userId);
        if(user != null)
        {
            String token = null;
            final Cookie[] cookies = request.getCookies();
            for (Cookie c: cookies
                 ) {
                if(c.getName().equals(SecurityConstants.TOKEN_HEADER)){
                    token = c.getValue();
                }
            }

            if (token != null )
            {
                String email = Jwts.parser()
                        .setSigningKey(SecurityConstants.JWT_SECRET.getBytes())
                        .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();
                if(email.equals(user.getEmail()))
                    return true;
            }
        }
        return false;
    }
}
