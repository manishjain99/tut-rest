package com.yummynoodlebar.rest.controller;

import static com.yummynoodlebar.rest.controller.fixture.RestEventFixtures.repoDetailsEvent;
import static com.yummynoodlebar.rest.controller.fixture.RestEventFixtures.repoDetailsNotFound;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.events.repos.RequestRepoDetailsEvent;
import com.yummynoodlebar.core.services.RepoService;

public class ViewRepoIntegrationTest {

  MockMvc mockMvc;

  @InjectMocks
  ReposQueriesController controller;

  @Mock
  RepoService repoService;

  RepoId key = RepoId.fromString("77");

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    this.mockMvc = standaloneSetup(controller)
            .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
  }

  @Test
  public void thatViewRepoUsesHttpNotFound() throws Exception {

    when(repoService.requestRepoDetails(any(RequestRepoDetailsEvent.class))).thenReturn(
            repoDetailsNotFound(key));

    this.mockMvc.perform(
            get("/api/repos/{id}",  key.toString())
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound());
  }

  @Test
  public void thatViewRepoUsesHttpOK() throws Exception {

    when(repoService.requestRepoDetails(any(RequestRepoDetailsEvent.class))).thenReturn(
            repoDetailsEvent(key));

    this.mockMvc.perform(
            get("/api/repos/{id}", key.toString())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  public void thatViewRepoRendersCorrectly() throws Exception {

    when(repoService.requestRepoDetails(any(RequestRepoDetailsEvent.class))).thenReturn(
            repoDetailsEvent(key));

    this.mockMvc.perform(
            get("/api/repos/{id}", key.toString())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value((int)(key.get().intValue())));//TODO remove complex conversion
  }
}
