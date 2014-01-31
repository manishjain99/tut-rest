package com.yummynoodlebar.rest.controller;

import static com.yummynoodlebar.rest.controller.fixture.RestEventFixtures.repoDeleted;
import static com.yummynoodlebar.rest.controller.fixture.RestEventFixtures.repoDeletedFailed;
import static com.yummynoodlebar.rest.controller.fixture.RestEventFixtures.repoDeletedNotFound;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.events.repos.DeleteRepoEvent;
import com.yummynoodlebar.core.services.RepoService;

public class CancelRepoIntegrationTest {

  MockMvc mockMvc;

  @InjectMocks
  RepositoryCommandsController controller;

  @Mock
  RepoService repoService;

  RepoId key = RepoId.fromString("55");

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    this.mockMvc = standaloneSetup(controller)
            .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

  }

  @Test
  public void thatDeleteRepoUsesHttpOkOnSuccess() throws Exception {

    when(repoService.deleteRepo(any(DeleteRepoEvent.class)))
            .thenReturn(
                    repoDeleted(key));

    this.mockMvc.perform(
            delete("/api/repos/{id}", key.toString())
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());

    verify(repoService).deleteRepo(argThat(
            Matchers.<DeleteRepoEvent>hasProperty("key",
                    Matchers.equalTo(key))));
  }

  @Test
  public void thatDeleteRepoUsesHttpNotFoundOnEntityLookupFailure() throws Exception {

    when(repoService.deleteRepo(any(DeleteRepoEvent.class)))
            .thenReturn(
                    repoDeletedNotFound(key));

    this.mockMvc.perform(
            delete("/aggregators/repos/{id}", key.toString())
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound());

  }

  @Test
  public void thatDeleteRepoUsesHttpForbiddenOnEntityDeletionFailure() throws Exception {

    when(repoService.deleteRepo(any(DeleteRepoEvent.class)))
            .thenReturn(
                    repoDeletedFailed(key));

    this.mockMvc.perform(
            delete("/api/repos/{id}", key.toString())
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isForbidden());
  }
}
