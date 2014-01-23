package com.yummynoodlebar.core.events.repos;

import com.yummynoodlebar.core.events.CreatedEvent;

import java.util.UUID;

public class RepoCreatedEvent extends CreatedEvent {

  private final UUID newRepoKey;
  private final RepoDetails details;

  public RepoCreatedEvent(final UUID newRepoKey, final RepoDetails details) {
    this.newRepoKey = newRepoKey;
    this.details = details;
  }

  public RepoDetails getDetails() {
    return details;
  }

  public UUID getNewRepoKey() {
    return newRepoKey;
  }
}
