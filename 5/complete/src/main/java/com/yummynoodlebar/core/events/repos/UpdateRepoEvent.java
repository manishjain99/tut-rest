package com.yummynoodlebar.core.events.repos;

import com.yummynoodlebar.core.events.UpdateEvent;

import java.util.UUID;

public class UpdateRepoEvent extends UpdateEvent {

  private UUID key;
  private RepoDetails repoDetails;

  public UpdateRepoEvent(UUID key, RepoDetails repoDetails) {
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
