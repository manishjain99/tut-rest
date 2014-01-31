package com.yummynoodlebar.core.events.repos;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.events.DeleteEvent;

public class DeleteRepoEvent extends DeleteEvent {

  private final RepoId key;

  public DeleteRepoEvent(final RepoId key) {
    this.key = key;
  }

  public RepoId getKey() {
    return key;
  }
}
