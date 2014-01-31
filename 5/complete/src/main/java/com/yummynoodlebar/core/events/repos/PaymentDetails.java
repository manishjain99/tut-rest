package com.yummynoodlebar.core.events.repos;

import java.util.Date;

import com.yummynoodelbar.common.RepoId;

public class PaymentDetails {

  private RepoId key;
  private Date dateTimeOfSubmission;



  public PaymentDetails() {
    key = null;
  }

  public PaymentDetails(RepoId key) {
    this.key = key;
  }

  public Date getDateTimeOfSubmission() {
    return this.dateTimeOfSubmission;
  }

  public void setDateTimeOfSubmission(Date dateTimeOfSubmission) {
    this.dateTimeOfSubmission = dateTimeOfSubmission;
  }

  public RepoId getKey() {
    return key;
  }

  public void setKey(RepoId key) {
    this.key = key;
  }
}
