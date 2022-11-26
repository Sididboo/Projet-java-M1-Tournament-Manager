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

        // GetPlayers

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

        // GetPlayer

        @Test
        void testGetPlayerById_shouldBeOk() throws Exception {
                // Test data
                playerRepository.save(Constant.P1);
                teamRepository.save(Constant.T1);

                MvcResult mvcResult = mockMvc.perform(get("/v1/player/1"))
                                .andExpect(status().isOk())
                                .andReturn();

                assertEquals("{\"id\":1,\"pseudo\":\"P1\",\"postalAdress\":\"24000\",\"teams\":[{\"id\":1,\"teamName\":\"T1\"}]}",
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

                mockMvc.perform(get("/v1/player/ee"))
                                .andExpect(status().isBadRequest());
        }

}
