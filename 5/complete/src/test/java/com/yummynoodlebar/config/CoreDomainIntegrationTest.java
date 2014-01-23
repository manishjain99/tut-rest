package com.yummynoodlebar.config;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yummynoodlebar.core.events.repos.AllReposEvent;
import com.yummynoodlebar.core.events.repos.CreateRepoEvent;
import com.yummynoodlebar.core.events.repos.RepoDetails;
import com.yummynoodlebar.core.events.repos.RequestAllReposEvent;
import com.yummynoodlebar.core.services.RepoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreConfig.class})
public class CoreDomainIntegrationTest {

  @Autowired
  RepoService repoService;

  //TODOCUMENT We have already asserted the correctness of the collaboration.
  //This is to check that the wiring in CoreConfig works.
  //We do this by inference.
  @Test
  public void addANewRepoToTheSystem() {

    CreateRepoEvent ev = new CreateRepoEvent(new RepoDetails());

    repoService.createRepo(ev);

    AllReposEvent allRepos = repoService.requestAllRepos(new RequestAllReposEvent());

    assertEquals(1, allRepos.getReposDetails().size());
  }
}
