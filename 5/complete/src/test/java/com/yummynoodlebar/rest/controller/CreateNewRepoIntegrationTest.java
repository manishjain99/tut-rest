package com.yummynoodlebar.rest.controller;

import com.yummynoodlebar.core.events.repos.CreateRepoEvent;
import com.yummynoodlebar.core.services.RepoService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.Mockito.*;
import static com.yummynoodlebar.rest.controller.fixture.RestDataFixture.*;
import static com.yummynoodlebar.rest.controller.fixture.RestEventFixtures.*;


public class CreateNewRepoIntegrationTest {

  MockMvc mockMvc;

  @InjectMocks
  RepositoryCommandsController controller;

  @Mock
  RepoService repoService;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    this.mockMvc = standaloneSetup(controller)
            .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

    when(repoService.createRepo(any(CreateRepoEvent.class))).thenReturn(
            repoCreated(UUID.fromString("f3512d26-72f6-4290-9265-63ad69eccc13")));
  }

  //createRepo - validation?

  @Test
  public void thatCreateRepoUsesHttpCreated() throws Exception {

    this.mockMvc.perform(
            post("/aggregators/repos")
                    .content(standardRepoJSON())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated());
  }

  @Test
  public void thatCreateRepoRendersAsJson() throws Exception {

    this.mockMvc.perform(
            post("/aggregators/repos")
                    .content(standardRepoJSON())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.items['" + YUMMY_ITEM + "']").value(12))
              .andExpect(jsonPath("$.key").value("f3512d26-72f6-4290-9265-63ad69eccc13"));
  }

  @Test
  public void thatCreateRepoPassesLocationHeader() throws Exception {

    this.mockMvc.perform(
            post("/aggregators/repos")
                    .content(standardRepoJSON())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(header().string("Location", Matchers.endsWith("/aggregators/repos/f3512d26-72f6-4290-9265-63ad69eccc13")));
  }
}
