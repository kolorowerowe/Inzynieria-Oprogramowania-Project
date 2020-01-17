package com.github.swapbook.api;

import com.github.swapbook.model.User;
import com.github.swapbook.repositories.users.UserService;
import com.github.swapbook.service.UserLoginService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringJUnit4ClassRunner.class)
public class UserTest {

    private static final int NUMBER_OF_USER_FIELDS = 6;

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private UserLoginService userLoginService;

    @InjectMocks
    private Users users;

    @Before
    public void setUp() throws Exception
    {
        mockMvc = MockMvcBuilders.standaloneSetup(users).build();
    }

    @Test
    public void getAllUsers_shouldReturnEmptyList() throws Exception {
        List<User> users = new LinkedList<User>();
        when(userService.getUsers()).thenReturn(users);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(0))
        );
    }

    @Test
    public void getAllUsers_shouldReturnListWithOneUser() throws Exception {
        User user1 = new User(1, "test1");
        List<User> users = new LinkedList<User>();
        users.add(user1);

        when(userService.getUsers()).thenReturn(users);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].user_id", Matchers.is(user1.getId()))
                );

        verify(userService).getUsers();
    }

    @Test
    public void getUserById_shouldReturnCorrectUser() throws Exception {
        User user1 = new User(1, "test1");

        when(userService.getUserById(user1.getId())).thenReturn(user1);

        when(userLoginService.VerifyToken(any(HttpServletRequest.class),anyInt())).thenReturn(true);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users/"+user1.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(NUMBER_OF_USER_FIELDS)))
                .andExpect(jsonPath("$.user_id", Matchers.is(user1.getId()))
                );

        verify(userService).getUserById(user1.getId());
    }
}
