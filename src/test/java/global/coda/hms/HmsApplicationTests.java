package global.coda.hms;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.ContentResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class HmsApplicationTests {
@Autowired

private MockMvc mock;
    @Test
    void contextLoads() throws Exception {
      ((ContentResultMatchers) this.mock.perform(get("/patients/{id}")).andDo(print()).andExpect(status().isOk())
      .andExpect((ResultMatcher) content())).string(containsString("{id}"));
    }
    

}
