package com.github.swapbook.api;

import com.github.swapbook.configuration.SecurityConstants;
import com.github.swapbook.model.Specimen;
import com.github.swapbook.model.User;
import com.github.swapbook.repositories.specimen.FakeSpecimenRepository;
import com.github.swapbook.repositories.specimen.SpecimenRepository;
import com.github.swapbook.repositories.users.FakeUserRepository;
import com.github.swapbook.repositories.users.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class Users {
    @Autowired
    UserRepository userRepository;

    @Autowired
    SpecimenRepository specimenRepository;

    public Users() {
        userRepository = new FakeUserRepository();
        specimenRepository = new FakeSpecimenRepository();
    }

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


    @GetMapping("api/users/specimens/{id}")
    @ResponseBody
    public ResponseEntity<List<Specimen>> getUsersSpecimens(HttpServletRequest request, @PathVariable(value = "id") int userId) {
        if(VerifyToken(request, userId))
            return ResponseEntity.ok().body(userRepository.getUserById(userId).getSpecimenList());
        else
            return  ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/api/users/specimens/{id}")
    public void addSpecimenToUser(HttpServletRequest request, @PathVariable(value = "id") int userId, @RequestBody Specimen specimen) {
        if(VerifyToken(request, userId))
        {
            specimen.setUserId(userId);
            specimenRepository.addToList(specimen);
            userRepository.addSpecimen(userId, specimen);
        }
    }

    @DeleteMapping("/api/users/specimens/{id}/{specimenId}")
    public void deleteSpecimenFromUser(HttpServletRequest request, @PathVariable(value = "id") int userId, @PathVariable(value = "specimenId") int specimenId) {
        if(VerifyToken(request, userId))
            userRepository.deleteSpecimen(userId, specimenId);
    }

    @GetMapping("/api/specimens/all")
    @ResponseBody
    public ResponseEntity<List<Specimen>> getSpecimens() {
        return ResponseEntity.ok().body(specimenRepository.getSpecimens());
    }

    private boolean VerifyToken(HttpServletRequest request, int userId)
    {
        User user = userRepository.getUserById(userId);
        if(user != null)
        {
            String token = request.getHeader(SecurityConstants.TOKEN_HEADER);

            String email = Jwts.parser()
                    .setSigningKey(SecurityConstants.JWT_SECRET.getBytes())
                    .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
            if(email.equals(user.getEmail()))
                return true;
        }
        return false;
    }
}
