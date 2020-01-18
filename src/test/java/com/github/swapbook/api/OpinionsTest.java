package com.github.swapbook.api;


import com.github.swapbook.model.Opinion;
import com.github.swapbook.repositories.opinions.OpinionService;
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
public class OpinionsTest{

    private MockMvc mockMvc;

    @Mock
    private OpinionService opinionService;

    @InjectMocks
    private Opinions opinions;

    @Before
    public void setUp() throws Exception
    {
        mockMvc = MockMvcBuilders.standaloneSetup(opinions).build();
    }

    @Test
    public void testGetAllOpinions() throws Exception {
        List<Opinion> opinions = new LinkedList<Opinion>();
        when(opinionService.getAllOpinions()).thenReturn(opinions);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/opinions/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(0))
                );
    }

    @Test
    public void testGetOpinionsFromUser() throws Exception {
        List<Opinion> opinions = new LinkedList<Opinion>();
        when(opinionService.getOpinionsFromUser(0)).thenReturn(opinions);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/opinions/giver/0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(0))
                );
    }

    @Test
    public void testGetOpinionsForUser() throws Exception {
        List<Opinion> opinions = new LinkedList<Opinion>();
        when(opinionService.getOpinionsForUser(0)).thenReturn(opinions);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/opinions/receiver/0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(0))
                );
    }

    @Test
    public void getAllOpinions() throws Exception {
        List<Opinion> opinions = new LinkedList<Opinion>();
        when(opinionService.getAllOpinions()).thenReturn(opinions);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/opinions/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(0))
                );
    }

    @Test
    public void getOpinionsFromUser() throws Exception {
        List<Opinion> opinions = new LinkedList<Opinion>();
        when(opinionService.getOpinionsFromUser(0)).thenReturn(opinions);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/opinions/giver/0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(0))
                );
    }

    @Test
    public void getOpinionsForUser() throws Exception {
        List<Opinion> opinions = new LinkedList<Opinion>();
        when(opinionService.getOpinionsForUser(0)).thenReturn(opinions);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/opinions/receiver/0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(0))
                );
    }

    @Test
    public void getOpinionById()  throws Exception {
        List<Opinion> opinions = new LinkedList<Opinion>();
        when(opinionService.getOpinionsForUser(0)).thenReturn(opinions);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/opinions/receiver/0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(0))
                );
    }
}