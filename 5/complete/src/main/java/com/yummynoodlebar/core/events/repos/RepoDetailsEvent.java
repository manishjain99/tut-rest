package com.yummynoodlebar.core.events.repos;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.events.ReadEvent;

public class RepoDetailsEvent extends ReadEvent {
  private RepoId key;
  private RepoDetails repoDetails;

  private RepoDetailsEvent(RepoId key) {
    this.key = key;
  }

  public RepoDetailsEvent(RepoId key, RepoDetails repoDetails) {
    this.key = key;
    this.repoDetails = repoDetails;
  }

  public RepoId getKey() {
    return key;
  }

  public RepoDetails getRepoDetails() {
    return repoDetails;
  }

  public static RepoDetailsEvent notFound(RepoId key) {
    RepoDetailsEvent ev = new RepoDetailsEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
