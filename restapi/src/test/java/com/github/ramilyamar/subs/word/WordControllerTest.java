package com.github.ramilyamar.subs.word;

import com.github.ramilyamar.subs.config.SpringConfiguration;
import com.github.ramilyamar.subs.config.TestSpringSecurityConfiguration;
import com.github.ramilyamar.subsreadfile.word.LearningStatus;
import com.github.ramilyamar.subsreadfile.word.WordDao;
import com.github.ramilyamar.subsreadfile.word.WordInfo;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@WithMockUser
@ContextConfiguration(classes = {TestSpringSecurityConfiguration.class, SpringConfiguration.class, WordController.class})
class WordControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WordDao wordDao;

    @Test
    void smokeTest() throws Exception {
        mvc.perform(get("/words").param("userId", "1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void getWordsTest() throws Exception {
        Collection<String> translations = Arrays.asList("он");
        List<WordInfo> words = Arrays.asList(new WordInfo("he", translations, 1, LearningStatus.NEW_WORD));
        Mockito.when(wordDao.getWordsByUserId(1)).thenReturn(words);

        mvc.perform(get("/words").param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].word", Matchers.equalToIgnoringCase("he")))
                .andDo(print())
                .andReturn();
    }

    @Test
    void getWordsFromMovieTest() throws Exception {
        Collection<String> translations = Arrays.asList("он");
        List<WordInfo> words = Arrays.asList(new WordInfo("he", translations, 1, LearningStatus.NEW_WORD));
        Mockito.when(wordDao.getWordsFromMovieByUserId(1, 1)).thenReturn(words);

        mvc.perform(get("/words")
                .param("userId", "1")
                .param("fileId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].word", Matchers.equalToIgnoringCase("he")))
                .andDo(print())
                .andReturn();
    }
}
