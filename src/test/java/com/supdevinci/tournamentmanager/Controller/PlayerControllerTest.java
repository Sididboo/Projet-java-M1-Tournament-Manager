package com.supdevinci.tournamentmanager.Controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.supdevinci.tournamentmanager.constant.Constant;
import com.supdevinci.tournamentmanager.repository.PlayerRepository;
import com.supdevinci.tournamentmanager.repository.TeamRepository;
import com.supdevinci.tournamentmanager.util.MockRequest;

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

    final String URL_TEMPLATE = "/v1/player";

    // #region createPlayer
    @Test
    @WithMockUser(username="admin")
    public void testCreatePlayer_shouldBeOk() throws Exception {
        MvcResult mvcResult = MockRequest.mockPostRequest(
                mockMvc,
                URL_TEMPLATE,
                "{\"pseudo\":\"P1\",\"postalAddress\":24000}",
                status().isCreated());

        assertEquals(
                "{\"id\":1,\"pseudo\":\"P1\",\"postalAddress\":\"24000\",\"teams\":null}",
                mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(username="admin")
    void testCreatePlayer_whenForgotParams_shouldBeBadRequest() throws Exception {
        // Without pseudo
        MockRequest.mockPostRequest(
                mockMvc,
                URL_TEMPLATE,
                "{\"postalAddress\": 24000}",
                status().isBadRequest());
        // Without postalAddress
        MockRequest.mockPostRequest(
                mockMvc,
                URL_TEMPLATE,
                "{\"pseudo\": \"P1\"",
                status().isBadRequest());

        // Need one assert to remove warning in this method
        Assert.assertTrue(true);
    }
    // #endregion

    // #region getPlayers
    @Test
    @WithMockUser(username="user")
    void testGetPlayers_shouldBeOk() throws Exception {
        // Test data
        playerRepository.save(Constant.P1);
        playerRepository.save(Constant.P2);

        MvcResult mvcResult = mockMvc.perform(get(URL_TEMPLATE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("[{\"id\":1,\"pseudo\":\"P1\"},{\"id\":2,\"pseudo\":\"P2\"}]",
                mvcResult.getResponse().getContentAsString());
    }
    // #endregion

    // #region getPlayerById
    @Test
    @WithMockUser(username="user")
    void testGetPlayerById_shouldBeOk() throws Exception {
        // Test data
        playerRepository.save(Constant.P1);
        teamRepository.save(Constant.T1);

        MvcResult mvcResult = mockMvc.perform(get(URL_TEMPLATE + "/1"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(
                "{\"id\":1,\"pseudo\":\"P1\",\"postalAddress\":\"24000\",\"teams\":[{\"id\":1,\"teamName\":\"T1\"}]}",
                mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(username="user")
    void testGetPlayerById_shouldBeNotFound() throws Exception {
        mockMvc.perform(get(URL_TEMPLATE + "/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username="user")
    void testGetPlayerById_shouldBeBadRequest() throws Exception {
        // Test data
        playerRepository.save(Constant.P1);

        mockMvc.perform(get(URL_TEMPLATE + "/aError400"))
                .andExpect(status().isBadRequest());
    }
    // #endregion

    // #region updatePlayer
    @Test
    @WithMockUser(username="admin")
    void testUpdatePlayer_shouldBeOk() throws Exception {
        // Test data
        playerRepository.save(Constant.P1);
        teamRepository.save(Constant.T1);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .put(URL_TEMPLATE + "/1")
                .content("{\"id\":1,\"pseudo\":\"P1\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated())
                .andReturn();

        assertEquals(

                "{\"id\":1,\"pseudo\":\"P1\",\"postalAddress\":\"24000\",\"teams\":[{\"id\":1,\"teamName\":\"T1\"}]}",
                mvcResult.getResponse().getContentAsString());

    }
    // #endregion
}
