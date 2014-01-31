package com.yummynoodlebar.rest.controller;

import static com.yummynoodlebar.rest.controller.fixture.RestEventFixtures.repoDetailsEvent;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import com.yummynoodlebar.core.events.repos.RequestRepoDetailsEvent;
import com.yummynoodlebar.core.services.RepoService;


public class ViewRepoXmlIntegrationTest {

  MockMvc mockMvc;

  @InjectMocks
  ReposQueriesController controller;

  @Mock
  RepoService repoService;

  RepoId repoId = new RepoId(55);

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    this.mockMvc = standaloneSetup(controller)
            .setMessageConverters(new MappingJackson2HttpMessageConverter(),
                                  new Jaxb2RootElementHttpMessageConverter()).build();
  }

  @Test
  public void thatViewRepoRendersXMLCorrectly() throws Exception {

    when(repoService.requestRepoDetails(any(RequestRepoDetailsEvent.class))).thenReturn(
            repoDetailsEvent(repoId));

    this.mockMvc.perform(
            get("/api/repos/{id}", repoId.toString())
                    .accept(MediaType.TEXT_XML))
            .andDo(print())
            .andExpect(content().contentType(MediaType.TEXT_XML))
            .andExpect(xpath("/repo/id").string(repoId.toString()));
  }

  @Test
  public void thatViewRepoRendersJsonCorrectly() throws Exception {

    when(repoService.requestRepoDetails(any(RequestRepoDetailsEvent.class))).thenReturn(
            repoDetailsEvent(repoId));

    //TODOCUMENT JSON Path in use here (really like this)

    this.mockMvc.perform(
            get("/api/repos/{id}", repoId.toString())
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(55));//TODO, use repoId.get but that throws error due to Long to Int conversion
  }
}
