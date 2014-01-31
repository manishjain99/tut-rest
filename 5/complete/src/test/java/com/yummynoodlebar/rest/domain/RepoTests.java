package com.yummynoodlebar.rest.domain;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.yummynoodlebar.core.events.repos.RepoDetails;
import com.yummynoodlebar.rest.controller.fixture.RestDataFixture;

public class RepoTests {

  @Test
  public void thatRepoCanConvertToRepoDetails() {
    Repo repo = RestDataFixture.standardRepo();

    RepoDetails details = repo.toRepoDetails();

    assertEquals("", repo.getId(), details.getId());
  }

  @Test
  public void thatRepoCanConvertFromRepoDetails() {
    RepoDetails details = RestDataFixture.standardRepoDetails();

    Repo repo = new Repo(details);

    assertEquals(repo.getRepoId(), details.getRepoId());
  }

}
