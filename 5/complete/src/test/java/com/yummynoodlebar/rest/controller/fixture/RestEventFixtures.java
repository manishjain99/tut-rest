package com.yummynoodlebar.rest.controller.fixture;

import static com.yummynoodlebar.rest.controller.fixture.RestDataFixture.customKeyRepoDetails;
import static com.yummynoodlebar.rest.controller.fixture.RestDataFixture.standardRepoDetails;

import java.util.Date;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.events.repos.RepoCreatedEvent;
import com.yummynoodlebar.core.events.repos.RepoDeletedEvent;
import com.yummynoodlebar.core.events.repos.RepoDetailsEvent;
import com.yummynoodlebar.core.events.repos.RepoStatusDetails;
import com.yummynoodlebar.core.events.repos.RepoStatusEvent;

public class RestEventFixtures {
  public static RepoStatusEvent repoStatusNotFound(RepoId key) {
    return RepoStatusEvent.notFound(key);
  }
  public static RepoStatusEvent repoStatus(RepoId key, String status) {
    return new RepoStatusEvent(key, new RepoStatusDetails(new Date(), status));
  }
  public static RepoDetailsEvent repoDetailsNotFound(RepoId key) {
    return RepoDetailsEvent.notFound(key);
  }
  public static RepoDetailsEvent repoDetailsEvent(RepoId key) {
    return new RepoDetailsEvent(key, customKeyRepoDetails(key));
  }
  public static RepoCreatedEvent repoCreated(RepoId key) {
    return new RepoCreatedEvent(key, customKeyRepoDetails(key));
  }
  public static RepoDeletedEvent repoDeleted(RepoId key) {
    return new RepoDeletedEvent(key, standardRepoDetails());
  }
  public static RepoDeletedEvent repoDeletedFailed(RepoId key) {
    return RepoDeletedEvent.deletionForbidden(key, standardRepoDetails());
  }
  public static RepoDeletedEvent repoDeletedNotFound(RepoId key) {
    return RepoDeletedEvent.notFound(key);
  }
}
