package com.yummynoodlebar.rest.controller.fixture;

import com.yummynoodlebar.core.events.repos.*;
import com.yummynoodlebar.rest.domain.Repo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

//TODOCUMENT.  Use of test data fixture classes is considered good practice.
/*
 The majority of tests aren't testing data edge cases, they are testing logical flows and
 what happens to a generic set of data.  For these, use a small, standardised set of fixtures.

 For anything more esoteric, create a new fixture in the test class.
 */
public class RestDataFixture {
  public static final String YUMMY_ITEM = "yummy1";

  public static AllReposEvent allRepos() {
    List<RepoDetails> repos = new ArrayList<RepoDetails>();

    repos.add(standardRepoDetails());
    repos.add(standardRepoDetails());
    repos.add(standardRepoDetails());

    return new AllReposEvent(repos);
  }

  public static Repo standardRepo() {
    Repo repo = new Repo();

    repo.setItems(Collections.singletonMap(YUMMY_ITEM, 12));

    return repo;
  }

  public static RepoDetails customKeyRepoDetails(UUID key) {
    RepoDetails repodetails = new RepoDetails(key);

    repodetails.setRepoItems(Collections.singletonMap(YUMMY_ITEM, 12));

    return repodetails;
  }
  public static RepoDetails standardRepoDetails() {
    return customKeyRepoDetails(UUID.randomUUID());
  }

  public static String standardRepoJSON() {
    return "{ \"items\": { \"yummy1\": 12, \"yummy15\": 42 } }";
  }
}
