package com.yummynoodlebar.rest.controller;

import com.yummynoodlebar.core.events.repos.RequestAllReposEvent;
import com.yummynoodlebar.core.services.RepoService;
import com.yummynoodlebar.rest.controller.fixture.RestDataFixture;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.yummynoodlebar.rest.controller.fixture.RestDataFixture.allRepos;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


public class GetAllReposIntegrationTest {

  MockMvc mockMvc;

  @InjectMocks
  ReposQueriesController controller;

  @Mock
  RepoService repoService;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    this.mockMvc = standaloneSetup(controller).build();

    when(repoService.requestAllRepos(any(RequestAllReposEvent.class))).thenReturn(allRepos());
  }

  @Test
  public void thatGetReposRendersAsJson() throws Exception {

    this.mockMvc.perform(
            get("/aggregators/repos")
              .accept(MediaType.APPLICATION_JSON))
              .andDo(print())
              .andExpect(status().isOk())
              .andExpect(jsonPath("$[0].items['" + RestDataFixture.YUMMY_ITEM + "']").value(12));
  }
}
