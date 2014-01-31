package com.yummynoodlebar.core.events.repos;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.events.DeletedEvent;

public class RepoDeletedEvent extends DeletedEvent {

  private RepoId key;
  private RepoDetails details;
  private boolean deletionCompleted;

  private RepoDeletedEvent(RepoId key) {
    this.key = key;
  }

  public RepoDeletedEvent(RepoId key, RepoDetails details) {
    this.key = key;
    this.details = details;
    this.deletionCompleted = true;
  }

  public RepoId getKey() {
    return key;
  }

  public RepoDetails getDetails() {
    return details;
  }

  public boolean isDeletionCompleted() {
    return deletionCompleted;
  }

  public static RepoDeletedEvent deletionForbidden(RepoId key, RepoDetails details) {
    RepoDeletedEvent ev = new RepoDeletedEvent(key, details);
    ev.entityFound=true;
    ev.deletionCompleted=false;
    return ev;
  }

  public static RepoDeletedEvent notFound(RepoId key) {
    RepoDeletedEvent ev = new RepoDeletedEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
