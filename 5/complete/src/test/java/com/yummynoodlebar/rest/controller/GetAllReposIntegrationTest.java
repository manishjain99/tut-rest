package com.yummynoodlebar.rest.controller;

import static com.yummynoodlebar.rest.controller.fixture.RestDataFixture.allRepos;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.yummynoodlebar.core.events.repos.RequestAllReposEvent;
import com.yummynoodlebar.core.services.RepoService;


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
            get("/api/repos")
              .accept(MediaType.APPLICATION_JSON))
              .andDo(print())
              .andExpect(status().isOk());
  }
}
