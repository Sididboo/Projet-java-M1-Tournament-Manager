package com.supdevinci.tournamentmanager.Controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

// Save this imports
/* 
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; 
*/

import com.supdevinci.tournamentmanager.constant.Constant;
import com.supdevinci.tournamentmanager.repository.PlayerRepository;
import com.supdevinci.tournamentmanager.repository.StateRepository;
import com.supdevinci.tournamentmanager.repository.TeamRepository;
import com.supdevinci.tournamentmanager.util.MockRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD) // Create database before each test
public class TournamentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private StateRepository stateRepository;

    final String URL_TEMPLATE = "/v1/tournament";

    // #region createTournament

    @Test
    void testCreateTournament_shouldBeOk() throws Exception {

        playerRepository.save(Constant.P1);
        playerRepository.save(Constant.P2);
        playerRepository.save(Constant.P3);
        teamRepository.save(Constant.T1);
        teamRepository.save(Constant.T2);
        stateRepository.save(Constant.S1);

        MvcResult mvcResult = MockRequest.mockPostRequest(
                mockMvc,
                URL_TEMPLATE,
                "{\"subject\": \"Tennis\", \"description\": \"Match de Tennis\", \"dateBegin\": \"2022-03-11\", \"teamIds\": [1,2]}",
                status().isCreated());

        assertEquals(
                "{\"id\":1,\"subject\":\"Tennis\",\"description\":\"Match de Tennis\",\"nameState\":\"PlanifiÃ©\",\"dateBegin\":\"2022-03-11T00:00:00.000+00:00\",\"teams\":[{\"id\":1,\"teamName\":\"T1\"},{\"id\":2,\"teamName\":\"T2\"}]}",
                mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testCreateTournament_WhenStateNotCreated_shouldReturnInternalServerErrorException() throws Exception {

        playerRepository.save(Constant.P1);
        playerRepository.save(Constant.P2);
        playerRepository.save(Constant.P3);
        teamRepository.save(Constant.T1);
        teamRepository.save(Constant.T2);

        MvcResult mvcResult = MockRequest.mockPostRequest(
                mockMvc,
                URL_TEMPLATE,
                "{\"subject\": \"Tennis\", \"description\": \"Match de Tennis\", \"dateBegin\": \"2022-03-11\", \"teamIds\": [1,2]}",
                status().isInternalServerError());

        assertEquals(
                "{\"code\":500,\"status\":\"Internal Server Error\",\"message\":\"It is possible that the tournament states are not created\",\"error\":\"INTERNAL_SERVER_ERROR\"}",
                mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testCreateTournament_WhenForgotParams_shouldReturnBadRequest() throws Exception {
        // Without subjet
        MockRequest.mockPostRequest(
                mockMvc,
                URL_TEMPLATE,
                "{\"description\": \"Match de Tennis\", \"dateBegin\": \"2022-03-11\", \"teamIds\": [1,2]}",
                status().isBadRequest());
        // Without description
        MockRequest.mockPostRequest(
                mockMvc,
                URL_TEMPLATE,
                "{\"subject\": \"Tennis\", \"dateBegin\": \"2022-03-11\", \"teamIds\": [1,2]}",
                status().isBadRequest());
        // Without dateBegin
        MockRequest.mockPostRequest(
                mockMvc,
                URL_TEMPLATE,
                "{\"subject\": \"Tennis\", \"description\": \"Match de Tennis\", \"teamIds\": [1,2]}",
                status().isBadRequest());
        // Without teamIds
        MockRequest.mockPostRequest(
                mockMvc,
                URL_TEMPLATE,
                "{\"subject\": \"Tennis\", \"description\": \"Match de Tennis\", \"dateBegin\": \"2022-03-11\"}",
                status().isBadRequest());

        // Need one assert to remove warning in this method
        Assert.assertTrue(true);
    }

    // #endregion

}
