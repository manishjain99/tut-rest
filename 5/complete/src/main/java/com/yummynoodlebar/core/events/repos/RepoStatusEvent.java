package com.yummynoodlebar.core.events.repos;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.events.ReadEvent;

public class RepoStatusEvent extends ReadEvent {
  private RepoId key;
  private RepoStatusDetails repoStatus;

  private RepoStatusEvent(RepoId key) {
    this.key = key;
  }

  public RepoStatusEvent(RepoId key, RepoStatusDetails repoStatus) {
    this.key = key;
    this.repoStatus = repoStatus;
  }

  public RepoId getKey() {
    return key;
  }

  public RepoStatusDetails getRepoStatus() {
    return repoStatus;
  }

  public static RepoStatusEvent notFound(RepoId key) {
    RepoStatusEvent ev = new RepoStatusEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
