package com.yummynoodlebar.core.events.repos;

import com.yummynoodlebar.core.events.ReadEvent;

import java.util.UUID;

public class RepoStatusEvent extends ReadEvent {
  private UUID key;
  private RepoStatusDetails repoStatus;

  private RepoStatusEvent(UUID key) {
    this.key = key;
  }

  public RepoStatusEvent(UUID key, RepoStatusDetails repoStatus) {
    this.key = key;
    this.repoStatus = repoStatus;
  }

  public UUID getKey() {
    return key;
  }

  public RepoStatusDetails getRepoStatus() {
    return repoStatus;
  }

  public static RepoStatusEvent notFound(UUID key) {
    RepoStatusEvent ev = new RepoStatusEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
