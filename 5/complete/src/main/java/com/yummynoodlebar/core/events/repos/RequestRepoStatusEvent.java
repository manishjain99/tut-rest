package com.yummynoodlebar.core.events.repos;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.events.RequestReadEvent;

public class RequestRepoStatusEvent extends RequestReadEvent {
  private RepoId key;

  public RequestRepoStatusEvent(RepoId key) {
    this.key = key;
  }

  public RepoId getKey() {
    return key;
  }
}
