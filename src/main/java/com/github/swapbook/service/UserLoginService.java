package com.github.swapbook.service;

import com.github.swapbook.configuration.SecurityConstants;
import com.github.swapbook.model.User;
import com.github.swapbook.repositories.users.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class UserLoginService {

    @Autowired
    UserService userService;

    public boolean VerifyToken(HttpServletRequest request, int userId)
    {
        User user = userService.getUserById(userId);
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
