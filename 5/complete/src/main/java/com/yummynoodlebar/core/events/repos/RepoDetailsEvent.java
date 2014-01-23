package com.yummynoodlebar.core.events.repos;

import com.yummynoodlebar.core.events.ReadEvent;

import java.util.UUID;

public class RepoDetailsEvent extends ReadEvent {
  private UUID key;
  private RepoDetails repoDetails;

  private RepoDetailsEvent(UUID key) {
    this.key = key;
  }

  public RepoDetailsEvent(UUID key, RepoDetails repoDetails) {
    this.key = key;
    this.repoDetails = repoDetails;
  }

  public UUID getKey() {
    return key;
  }

  public RepoDetails getRepoDetails() {
    return repoDetails;
  }

  public static RepoDetailsEvent notFound(UUID key) {
    RepoDetailsEvent ev = new RepoDetailsEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
