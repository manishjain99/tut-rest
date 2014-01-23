package com.yummynoodlebar.core.events.repos;

import java.util.Date;

public class RepoStatusDetails {

  private Date statusDate;
  private String status;

  public RepoStatusDetails(Date statusDate, String status) {
    this.status = status;
    this.statusDate = statusDate;
  }

  public Date getStatusDate() {
    return statusDate;
  }

  public String getStatus() {
    return status;
  }
}
