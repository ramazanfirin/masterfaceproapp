package com.mastertek.web.rest;

import com.mastertek.MasterfaceproApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Test class for the Terminal REST controller.
 *
 * @see TerminalResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MasterfaceproApp.class)
public class TerminalResourceIntTest {

    private MockMvc restMockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        TerminalResource terminalResource = new TerminalResource();
        restMockMvc = MockMvcBuilders
            .standaloneSetup(terminalResource)
            .build();
    }

    /**
    * Test strangerSubscription
    */
    @Test
    public void testStrangerSubscription() throws Exception {
        restMockMvc.perform(post("/api/terminal/stranger-subscription"))
            .andExpect(status().isOk());
    }
    /**
    * Test verifySubscription
    */
    @Test
    public void testVerifySubscription() throws Exception {
        restMockMvc.perform(post("/api/terminal/verify-subscription"))
            .andExpect(status().isOk());
    }

}
