package com.yummynoodlebar.core.events.repos;

import java.util.Collections;
import java.util.List;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodelbar.common.RepoStatus;
import com.yummynoodlebar.core.events.CreatedEvent;

public class RepoCreatedEvent extends CreatedEvent {

  private final RepoId newRepoKey;
  private final RepoDetails details;
  private final List<RepoStatus> statusHistory ;
  
  public RepoCreatedEvent(){
	  throw new RuntimeException("We do not allow for emptyEvent, use different constrctor");
  }

  public RepoCreatedEvent(RepoId newRepoKey, RepoDetails details) {
    this.newRepoKey = newRepoKey;
    this.details = details;
    this.statusHistory = details.getStatusHistory();
  }

  public RepoDetails getDetails() {
    return details;
  }

  public long getNewRepoKey() {
    return newRepoKey.get();
  }
  public List<RepoStatus> getStatusHistory(){
	  return Collections.unmodifiableList(statusHistory);
  }
}
