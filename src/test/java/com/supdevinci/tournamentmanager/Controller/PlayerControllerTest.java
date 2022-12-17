package com.supdevinci.tournamentmanager.Controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.supdevinci.tournamentmanager.constant.Constant;
import com.supdevinci.tournamentmanager.repository.PlayerRepository;
import com.supdevinci.tournamentmanager.repository.TeamRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD) // Create database before each test
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;

    // #region getPlayers

    /**
     * Test POST Player
     */
    @Test
    public void testCreatePlayer_shouldBeOk() throws Exception {
        // Test data
        playerRepository.save(Constant.P1);
        playerRepository.save(Constant.P2);
        playerRepository.save(Constant.P3);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/player")
                        .content("{\"pseudo\": \"P4\", \"postalAddress\": 24003}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertEquals(
                "{\"id\":4,\"pseudo\":\"P4\"}",
                mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testCreatePlayer_shouldBeBadRequest_withTheOmissionOfThePseudo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/player")
                        .content("{\"postalAddress\": 24000}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreatePlayer_shouldBeBadRequest_withTheOmissionOfThePostalAddress() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/team")
                        .content("{\"pseudo\": \"P1\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test GET Player
     */
    @Test
    void testGetPlayers_shouldBeOk() throws Exception {
        // Test data
        playerRepository.save(Constant.P1);
        playerRepository.save(Constant.P2);

        MvcResult mvcResult = mockMvc.perform(get("/v1/player"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("[{\"id\":1,\"pseudo\":\"P1\"},{\"id\":2,\"pseudo\":\"P2\"}]",
                mvcResult.getResponse().getContentAsString());
    }

    // #endregion

    // #region getPlayerById

    @Test
    void testGetPlayerById_shouldBeOk() throws Exception {
        // Test data
        playerRepository.save(Constant.P1);
        teamRepository.save(Constant.T1);

        MvcResult mvcResult = mockMvc.perform(get("/v1/player/1"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(
                "{\"id\":1,\"pseudo\":\"P1\",\"postalAddress\":\"24000\",\"teams\":[{\"id\":1,\"teamName\":\"T1\"}]}",
                mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testGetPlayerById_shouldBeNotFound() throws Exception {
        mockMvc.perform(get("/v1/player/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetPlayerById_shouldBeBadRequest() throws Exception {
        // Test data
        playerRepository.save(Constant.P1);

        mockMvc.perform(get("/v1/player/aError400"))
                .andExpect(status().isBadRequest());
    }

    // #endregion

    // #region createPlayer

    @Test
    public void testCreatePlayer_shouldBeOk() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/player")
                .content("{\"pseudo\": \"P1\", \"postalAddress\": 24000}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

    /**
     * Test PUT Player
     */
    @Test
    void testPutPlayersById_shouldBeOk() throws Exception{
        // Test data
        playerRepository.save(Constant.P1);
        teamRepository.save(Constant.T1);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/player/1")
                        .content("{\"id\":1,\"pseudo\":\"P1_bis\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated())
                .andReturn();

        assertEquals(

                "{\"id\":1,\"pseudo\":\"P1\",\"postalAddress\":\"24000\",\"teams\":null}",
                mvcResult.getResponse().getContentAsString());

    }

    @Test
    void testCreatePlayer_shouldBeBadRequest_withoutData() throws Exception {
        // Without speudo
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/player")
                .content("{\"postalAddress\": 24000}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        // Without postalAddress
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/team")
                .content("{\"pseudo\": \"P1\"")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    // #endregion
                "{\"id\":1,\"pseudo\":\"P1_bis\"}",
                mvcResult.getResponse().getContentAsString());
    }
}
