package com.supdevinci.tournamentmanager.util;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// Save this imports
/* 
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; 
*/

/**
 * Generic class to mock requests 
 */
public class MockRequest {

    /**
     * Mock post request.
     * 
     * @param mockMvc
     * @param urlTemplate
     * @param content
     * @param httpStatus
     * @return response of request
     * @throws Exception
     */
    public static MvcResult mockPostRequest(MockMvc mockMvc, String urlTemplate, String content,
            ResultMatcher httpStatus) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .post(urlTemplate)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(httpStatus)
                .andDo(print())
                .andReturn();
    }

    /**
     * Mock put request.
     * 
     * @param mockMvc
     * @param urlTemplate
     * @param content
     * @param httpStatus
     * @return response of request
     * @throws Exception
     */
    public static MvcResult mockPutRequest(MockMvc mockMvc, String urlTemplate, String content,
            ResultMatcher httpStatus) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .put(urlTemplate)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(httpStatus)
                .andDo(print())
                .andReturn();
    }

}
