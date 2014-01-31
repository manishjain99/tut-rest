package com.yummynoodlebar.rest.controller;

import static com.yummynoodlebar.rest.controller.fixture.RestEventFixtures.repoStatus;
import static com.yummynoodlebar.rest.controller.fixture.RestEventFixtures.repoStatusNotFound;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.events.repos.RequestRepoStatusEvent;
import com.yummynoodlebar.core.services.RepoService;

public class RepoStatusIntegrationTest {

  MockMvc mockMvc;

  @InjectMocks
  RepoStatusController controller;

  @Mock
  RepoService repoService;

  RepoId key = RepoId.fromString("88");

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
            get("/api/repos/{id}/status", key.toString())
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  public void thatViewRepoRendersJSONCorrectly() throws Exception {

    when(repoService.requestRepoStatus(any(RequestRepoStatusEvent.class))).thenReturn(
            repoStatus(key, "Cooking"));

    this.mockMvc.perform(
            get("/api/repos/{id}/status", key.toString())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(jsonPath("$.status").value("Cooking"));
  }

  @Test
  public void thatViewRepoRendersXMLCorrectly() throws Exception {

    when(repoService.requestRepoStatus(any(RequestRepoStatusEvent.class))).thenReturn(
            repoStatus(key, "Cooking"));

    this.mockMvc.perform(
            get("/api/repos/{id}/status", key.toString())
                    .accept(MediaType.TEXT_XML))
            .andDo(print())
            .andExpect(content().contentType(MediaType.TEXT_XML))
            .andExpect(xpath("/repoStatus/status").string("Cooking"));
  }
}
