package com.supdevinci.tournamentmanager.Controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Save this imports
/* 
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; 
*/

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.supdevinci.tournamentmanager.constant.Constant;
import com.supdevinci.tournamentmanager.repository.PlayerRepository;
import com.supdevinci.tournamentmanager.repository.TeamRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD) // Create database before each test
public class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;

    // GetPlayers

    @Test
    void testGetPlayers_shouldBeOk() throws Exception {
        // Test data
        playerRepository.save(Constant.P1);
        playerRepository.save(Constant.P2);
        playerRepository.save(Constant.P3);
        teamRepository.save(Constant.T1);
        teamRepository.save(Constant.T2);

        MvcResult mvcResult = mockMvc.perform(get("/v1/team"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(
                "[{\"id\":1,\"teamName\":\"T1\",\"players\":[{\"id\":1,\"pseudo\":\"P1\"}]},{\"id\":2,\"teamName\":\"T2\",\"players\":[{\"id\":2,\"pseudo\":\"P2\"},{\"id\":3,\"pseudo\":\"P3\"}]}]",
                mvcResult.getResponse().getContentAsString());
    }

}
