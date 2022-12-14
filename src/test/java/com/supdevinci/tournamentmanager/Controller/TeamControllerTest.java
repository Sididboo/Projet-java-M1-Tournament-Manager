package com.supdevinci.tournamentmanager.Controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Save this imports
/* 
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.supdevinci.tournamentmanager.constant.Constant;
import com.supdevinci.tournamentmanager.repository.PlayerRepository;
import com.supdevinci.tournamentmanager.repository.StateRepository;
import com.supdevinci.tournamentmanager.repository.TeamRepository;
import com.supdevinci.tournamentmanager.repository.TournamentRepository;

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
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private TournamentRepository tournamentRepository;

    // #region getTeams

    @Test
    void testGetTeams_shouldBeOk() throws Exception {
        // Test data
        playerRepository.save(Constant.P1);
        playerRepository.save(Constant.P2);
        playerRepository.save(Constant.P3);
        teamRepository.save(Constant.T1);
        teamRepository.save(Constant.T2);

        MvcResult mvcResult = mockMvc.perform(get("/v1/team"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(
                "[{\"id\":1,\"teamName\":\"T1\",\"players\":[{\"id\":1,\"pseudo\":\"P1\"}]},{\"id\":2,\"teamName\":\"T2\",\"players\":[{\"id\":2,\"pseudo\":\"P2\"},{\"id\":3,\"pseudo\":\"P3\"}]}]",
                mvcResult.getResponse().getContentAsString());
    }

    // #endregion

    // #region getTeamById

    @Test
    void testGetTeamById_shouldBeOk() throws Exception {
        // Test data
        playerRepository.save(Constant.P1);
        playerRepository.save(Constant.P2);
        playerRepository.save(Constant.P3);
        teamRepository.save(Constant.T1);
        teamRepository.save(Constant.T2);
        stateRepository.save(Constant.S1);
        stateRepository.save(Constant.S2);
        stateRepository.save(Constant.S3);
        tournamentRepository.save(Constant.TO1);
        tournamentRepository.save(Constant.TO2);
        tournamentRepository.save(Constant.TO3);

        MvcResult mvcResult = mockMvc.perform(get("/v1/team/1"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("{\"id\":1,\"teamName\":\"T1\",\"players\":[{\"id\":1,\"pseudo\":\"P1\"}],\"nbVictories\":1}",
                mvcResult.getResponse().getContentAsString());

    }

    @Test
    void testGetTeamById_shouldBeNotFound() throws Exception {
        mockMvc.perform(get("/v1/team/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetTeamById_shouldBeBadRequest() throws Exception {
        mockMvc.perform(get("/v1/team/ee"))
                .andExpect(status().isBadRequest());
    }

    // #endregion

    // #region createTeam

    @Test
    void testCreateTeam_shouldBeOk() throws Exception {
        // Test data
        playerRepository.save(Constant.P1);
        playerRepository.save(Constant.P2);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/team")
                .content("{\"teamName\": \"T1\", \"playerIds\": [1, 2]}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertEquals(
                "{\"id\":1,\"teamName\":\"T1\",\"players\":[{\"id\":1,\"pseudo\":\"P1\"},{\"id\":2,\"pseudo\":\"P2\"}],\"nbVictories\":0}",
                mvcResult.getResponse().getContentAsString());

    }

    @Test
    void testCreateTeam_shouldBeBadRequest_withoutData() throws Exception {
        // Without teamName
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/team")
                .content("{\"playerIds\": [1, 2]}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        // Without list players
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/team")
                .content("{\"teamName\": \"T1\"")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    // #endregion

}
