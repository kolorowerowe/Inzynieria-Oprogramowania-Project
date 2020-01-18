package com.github.swapbook.api;

import com.github.swapbook.email.EmailService;
import com.github.swapbook.model.Loan;
import com.github.swapbook.model.Specimen;
import com.github.swapbook.model.User;
import com.github.swapbook.repositories.loans.LoanService;
import com.github.swapbook.repositories.specimens.SpecimenService;
import com.github.swapbook.repositories.users.UserService;
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

import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoanApiTest {

    private MockMvc mockMvc;

    @Mock
    UserService userService;

    @Mock
    LoanService loanService;

    @Mock
    SpecimenService specimenService;

    @Mock
    EmailService emailService;

    @InjectMocks
    private LoanApi loanApi;

    @Before
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(loanApi).build();
    }


    @Test
    public void createLoan() throws Exception {
        List<Loan> list = new LinkedList<Loan>();
        when(loanService.getAllLoans()).thenReturn(list);

        Specimen sp = new Specimen(1,1,2,"title","",100,"", "", "", "", 14,"", "");
        when(specimenService.getSpecimenById(1)).thenReturn(sp);

        User owner = new User(2, "owner", "owner@gmail.com", "", "");
        User loaner = new User(1, "loaner", "loaner@gmail.com", "", "");
        when(userService.getUserById(sp.getUser_id())).thenReturn(owner);
        when(userService.getUserById(1)).thenReturn(loaner);
        Loan loan = new Loan();
        loan.setLoan_id(10);
        when(loanService.addLoanToList(any())).thenReturn(loan);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/loans/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"userId\": 1,\n" +
                                "    \"specimenId\": 1\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(emailService).sendMessage(eq(owner.getEmail()), anyString(), anyString(), eq(true));
    }
}