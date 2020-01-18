package com.github.swapbook.api;

import com.github.swapbook.model.Loan;
import com.github.swapbook.repositories.loans.LoanService;
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

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoansTest {

    private MockMvc mockMvc;

    @Mock
    private LoanService loanService;

    @InjectMocks
    private Loans loans;

    @Before
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(loans).build();
    }

    @Test
    public void getAllLoans() throws Exception {
        List<Loan> list = new LinkedList<Loan>();
        when(loanService.getAllLoans()).thenReturn(list);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/loans/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(0))
                );
    }

    @Test
    public void getLoansForState() throws Exception {
        List<Loan> list = new LinkedList<Loan>();
        when(loanService.getLoansInState("AVAILABLE")).thenReturn(list);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/loans/state/AVAILABLE"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(0))
                );
    }

    @Test
    public void getLoansFromUser() throws Exception {
        List<Loan> list = new LinkedList<Loan>();
        when(loanService.getLoansFromUser(0)).thenReturn(list);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/loans/owner/0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(0))
                );

    }

    @Test
    public void getLoansToUser() throws Exception {
        List<Loan> list = new LinkedList<Loan>();
        when(loanService.getLoansToUser(0)).thenReturn(list);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/loans/loaner/0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(0))
                );
    }
}