package com.yummynoodelbar.common;

import java.util.Set;


public class RepoStatusHistory {

  private final Set<RepoStatus> repoStatuses;

  public RepoStatusHistory(final Set<RepoStatus> repoStatuses) {
    this.repoStatuses = repoStatuses;
  }

  public void addStatus(final RepoStatus repoStatus) {
    this.repoStatuses.add(repoStatus);
  }
}
