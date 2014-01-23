package com.yummynoodlebar.core.events.repos;

import com.yummynoodlebar.core.events.ReadEvent;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AllReposEvent extends ReadEvent {

  private final List<RepoDetails> reposDetails;

  public AllReposEvent(List<RepoDetails> repos) {
    this.reposDetails = Collections.unmodifiableList(repos);
  }

  public Collection<RepoDetails> getReposDetails() {
    return this.reposDetails;
  }
}
