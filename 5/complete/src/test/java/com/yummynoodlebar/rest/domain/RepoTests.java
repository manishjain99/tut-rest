package com.yummynoodlebar.rest.domain;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

import org.junit.Test;

import com.yummynoodlebar.core.events.repos.RepoDetails;
import com.yummynoodlebar.rest.controller.fixture.RestDataFixture;

public class RepoTests {

  @Test
  public void thatRepoCanConvertToRepoDetails() {
    Repo repo = RestDataFixture.standardRepo();

    RepoDetails details = repo.toRepoDetails();

    assertEquals(repo.getKey(), details.getKey());
    assertEquals(repo.getDateTimeOfSubmission(), details.getDateTimeOfSubmission());
    assertEquals(details.getRepoItems().size(), details.getRepoItems().size());
    assertTrue(details.getRepoItems().containsKey(RestDataFixture.YUMMY_ITEM));
    assertEquals(details.getRepoItems().get(RestDataFixture.YUMMY_ITEM), repo.getItems().get(RestDataFixture.YUMMY_ITEM));
  }

  @Test
  public void thatRepoCanConvertFromRepoDetails() {
    RepoDetails details = RestDataFixture.standardRepoDetails();

    Repo repo = Repo.fromRepoDetails(details);

    assertEquals(repo.getKey(), details.getKey());
    assertEquals(repo.getDateTimeOfSubmission(), details.getDateTimeOfSubmission());
    assertEquals(repo.getItems().size(), details.getRepoItems().size());
    assertTrue(repo.getItems().containsKey(RestDataFixture.YUMMY_ITEM));
    assertEquals(details.getRepoItems().get(RestDataFixture.YUMMY_ITEM), repo.getItems().get(RestDataFixture.YUMMY_ITEM));
  }

}
