package com.yummynoodlebar.core.events.repos;

import com.yummynoodlebar.core.events.UpdatedEvent;

import java.util.UUID;

public class RepoUpdatedEvent extends UpdatedEvent {

  private UUID key;
  private RepoDetails repoDetails;

  public RepoUpdatedEvent(UUID key, RepoDetails repoDetails) {
    this.key = key;
    this.repoDetails = repoDetails;
  }

  public UUID getKey() {
    return key;
  }

  public RepoDetails getRepoDetails() {
    return repoDetails;
  }
}
