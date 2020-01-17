package com.github.swapbook.api;

import com.github.swapbook.configuration.SecurityConstants;
import com.github.swapbook.model.User;
import com.github.swapbook.repositories.users.UserService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoginTest {
    private MockMvc mockMvc;

    private static final int NUMBER_OF_USER_FIELDS = 6;

    @Mock
    private UserService userService;

    @InjectMocks
    private Login login;

    @Before
    public void setUp() throws Exception
    {
        mockMvc = MockMvcBuilders.standaloneSetup(login).build();
    }

    @Test
    public void getAllUsers_shouldReturnEmptyList() throws Exception {
        User user1 = new User(1, "test1", "test@test.pl", "haslo", "address");

        when(userService.getUserByEmail(user1.getEmail())).thenReturn(user1);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"email\": \"test@test.pl\",\n" +
                                "    \"password\": \"haslo\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(NUMBER_OF_USER_FIELDS)))
                .andExpect(jsonPath("$.user_id", Matchers.is(user1.getId())))
                .andExpect(jsonPath("$.name", Matchers.is(user1.getName())))
                .andExpect(jsonPath("$.email", Matchers.is(user1.getEmail())))
                .andExpect(jsonPath("$.address", Matchers.is(user1.getAddress())))
                .andExpect(MockMvcResultMatchers.cookie().exists(SecurityConstants.TOKEN_HEADER)
                );
    }
}
