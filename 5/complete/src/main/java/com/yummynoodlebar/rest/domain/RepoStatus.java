package com.yummynoodlebar.rest.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.events.repos.RepoStatusDetails;

@XmlRootElement
public class RepoStatus {

  @XmlElement
  private RepoId repoId;

  @XmlElement
  private Date statusDate;

  @XmlElement
  private String status;

  public static RepoStatus fromRepoStatusDetails(RepoId key, RepoStatusDetails repoDetails) {
    RepoStatus status = new RepoStatus();

    status.repoId = key;
    status.status = repoDetails.getStatus();
    status.statusDate = repoDetails.getStatusDate();

    return status;
  }
  @JsonIgnore
  public RepoId getRepoId() {
    return repoId;
  }

  public Date getStatusDate() {
    return statusDate;
  }

  public String getStatus() {
    return status;
  }
}
