package com.yummynoodlebar.core.events.repos;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.events.UpdateEvent;

public class UpdateRepoEvent extends UpdateEvent {

  private RepoId key;
  private RepoDetails repoDetails;

  public UpdateRepoEvent(RepoId key, RepoDetails repoDetails) {
    this.key = key;
    this.repoDetails = repoDetails;
  }

  public RepoId getKey() {
    return key;
  }

  public RepoDetails getRepoDetails() {
    return repoDetails;
  }
}
