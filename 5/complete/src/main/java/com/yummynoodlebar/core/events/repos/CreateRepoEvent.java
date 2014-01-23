package com.yummynoodlebar.core.events.repos;

import com.yummynoodlebar.core.events.CreateEvent;

public class CreateRepoEvent extends CreateEvent {
  private RepoDetails details;

  public CreateRepoEvent(RepoDetails details) {
    this.details = details;
  }

  public RepoDetails getDetails() {
    return details;
  }
  
}
