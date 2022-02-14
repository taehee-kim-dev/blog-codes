package taeheekim.applicationeventpublisher.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("ApplicationEventPublisher 테스트")
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Spring 4.2 버전 이전방식(MyEventOld) 테스트")
    @Test
    void publishMyEventOld() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(get("/old/api/publish-event?data=oldData"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value("oldData"))
        ;
    }

    @DisplayName("Spring 4.2 버전 부터의 방식(MyEventNew) 테스트")
    @Test
    void publishMyEventNew() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(get("/new/api/publish-event?data=newData"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value("newData"))
        ;
    }
}
