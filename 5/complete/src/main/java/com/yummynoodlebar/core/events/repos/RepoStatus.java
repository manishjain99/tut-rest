package com.yummynoodlebar.core.events.repos;

import java.util.UUID;

public class RepoStatus {

  private UUID key;

  public RepoStatus() {
    key = null;
  }

  public RepoStatus(UUID key) {
    this.key = key;
  }

  public UUID getKey() {
    return key;
  }

  public void setKey(UUID key) {
    this.key = key;
  }
}
