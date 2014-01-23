package com.yummynoodlebar.core.domain;

import java.util.Date;

import com.yummynoodlebar.core.events.repos.RepoStatusDetails;

public class RepoStatus {

  private Date statusDate;
  private String status;

  public RepoStatus(final Date date, final String status) {
    this.status = status;
    this.statusDate = date;
  }

  public Date getStatusDate() {
    return statusDate;
  }

  public String getStatus() {
    return status;
  }

  public RepoStatusDetails toStatusDetails() {
    return new RepoStatusDetails(statusDate, status);
  }
}
