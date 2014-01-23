package com.yummynoodlebar.core.events.repos;

import com.yummynoodlebar.core.events.DeletedEvent;

import java.util.UUID;

public class RepoDeletedEvent extends DeletedEvent {

  private UUID key;
  private RepoDetails details;
  private boolean deletionCompleted;

  private RepoDeletedEvent(UUID key) {
    this.key = key;
  }

  public RepoDeletedEvent(UUID key, RepoDetails details) {
    this.key = key;
    this.details = details;
    this.deletionCompleted = true;
  }

  public UUID getKey() {
    return key;
  }

  public RepoDetails getDetails() {
    return details;
  }

  public boolean isDeletionCompleted() {
    return deletionCompleted;
  }

  public static RepoDeletedEvent deletionForbidden(UUID key, RepoDetails details) {
    RepoDeletedEvent ev = new RepoDeletedEvent(key, details);
    ev.entityFound=true;
    ev.deletionCompleted=false;
    return ev;
  }

  public static RepoDeletedEvent notFound(UUID key) {
    RepoDeletedEvent ev = new RepoDeletedEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
