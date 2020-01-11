package com.github.ramilyamar.subs.word;

import com.github.ramilyamar.subs.config.SpringConfiguration;
import com.github.ramilyamar.subs.config.TestSpringSecurityConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = {TestSpringSecurityConfiguration.class, SpringConfiguration.class, WordController.class})
class WordControllerTest {

    @Autowired
    private MockMvc mvc;

    @WithMockUser
    @Test
    void smokeTest() throws Exception {
        mvc.perform(get("/words").param("userId", "1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }
}
