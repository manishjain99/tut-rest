package com.yummynoodlebar.core.events.repos;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.events.UpdateEvent;

public class SetPaymentEvent extends UpdateEvent {

  private RepoId key;
  private RepoDetails repoDetails;

  public SetPaymentEvent(RepoId key, RepoDetails repoDetails) {
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
