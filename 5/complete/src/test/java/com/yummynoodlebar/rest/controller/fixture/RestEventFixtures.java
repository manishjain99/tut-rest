package com.yummynoodlebar.rest.controller.fixture;

import com.yummynoodlebar.core.events.repos.*;

import static com.yummynoodlebar.rest.controller.fixture.RestDataFixture.*;

import java.util.Date;
import java.util.UUID;

public class RestEventFixtures {
  public static RepoStatusEvent repoStatusNotFound(UUID key) {
    return RepoStatusEvent.notFound(key);
  }
  public static RepoStatusEvent repoStatus(UUID key, String status) {
    return new RepoStatusEvent(key, new RepoStatusDetails(new Date(), status));
  }
  public static RepoDetailsEvent repoDetailsNotFound(UUID key) {
    return RepoDetailsEvent.notFound(key);
  }
  public static RepoDetailsEvent repoDetailsEvent(UUID key) {
    return new RepoDetailsEvent(key, customKeyRepoDetails(key));
  }
  public static RepoCreatedEvent repoCreated(UUID key) {
    return new RepoCreatedEvent(key, customKeyRepoDetails(key));
  }
  public static RepoDeletedEvent repoDeleted(UUID key) {
    return new RepoDeletedEvent(key, standardRepoDetails());
  }
  public static RepoDeletedEvent repoDeletedFailed(UUID key) {
    return RepoDeletedEvent.deletionForbidden(key, standardRepoDetails());
  }
  public static RepoDeletedEvent repoDeletedNotFound(UUID key) {
    return RepoDeletedEvent.notFound(key);
  }
}
