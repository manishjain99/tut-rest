package com.yummynoodlebar.rest.controller;

import com.yummynoodlebar.core.events.repos.RequestRepoStatusEvent;
import com.yummynoodlebar.core.services.RepoService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.yummynoodlebar.rest.controller.fixture.RestEventFixtures.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class RepoStatusIntegrationTest {

  MockMvc mockMvc;

  @InjectMocks
  RepoStatusController controller;

  @Mock
  RepoService repoService;

  UUID key = UUID.fromString("f3512d26-72f6-4290-9265-63ad69eccc13");

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    this.mockMvc = standaloneSetup(controller)
            .setMessageConverters(
                    new MappingJackson2HttpMessageConverter(),
                    new Jaxb2RootElementHttpMessageConverter()).build();
  }

  @Test
  public void thatViewRepoStatusUsesHttpNotFound() throws Exception {

    when(repoService.requestRepoStatus(any(RequestRepoStatusEvent.class))).thenReturn(
            repoStatusNotFound(key));

    this.mockMvc.perform(
            get("/aggregators/repos/{id}/status", key.toString())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
  }

  @Test
  public void thatViewRepoUsesHttpOK() throws Exception {

    when(repoService.requestRepoStatus(any(RequestRepoStatusEvent.class))).thenReturn(
            repoStatus(key, "Cooking"));

    this.mockMvc.perform(
            get("/aggregators/repos/{id}/status", key.toString())
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  public void thatViewRepoRendersJSONCorrectly() throws Exception {

    when(repoService.requestRepoStatus(any(RequestRepoStatusEvent.class))).thenReturn(
            repoStatus(key, "Cooking"));

    this.mockMvc.perform(
            get("/aggregators/repos/{id}/status", key.toString())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.repoId").value(key.toString()))
            .andExpect(jsonPath("$.status").value("Cooking"));
  }

  @Test
  public void thatViewRepoRendersXMLCorrectly() throws Exception {

    when(repoService.requestRepoStatus(any(RequestRepoStatusEvent.class))).thenReturn(
            repoStatus(key, "Cooking"));

    this.mockMvc.perform(
            get("/aggregators/repos/{id}/status", key.toString())
                    .accept(MediaType.TEXT_XML))
            .andDo(print())
            .andExpect(content().contentType(MediaType.TEXT_XML))
            .andExpect(xpath("/repoStatus/repoId").string(key.toString()))
            .andExpect(xpath("/repoStatus/status").string("Cooking"));
  }
}
