package com.yummynoodlebar.rest.domain;

import com.yummynoodlebar.core.events.repos.RepoStatusDetails;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.UUID;

@XmlRootElement
public class RepoStatus {

  @XmlElement
  private UUID repoId;

  @XmlElement
  private Date statusDate;

  @XmlElement
  private String status;

  public static RepoStatus fromRepoStatusDetails(UUID key, RepoStatusDetails repoDetails) {
    RepoStatus status = new RepoStatus();

    status.repoId = key;
    status.status = repoDetails.getStatus();
    status.statusDate = repoDetails.getStatusDate();

    return status;
  }

  public UUID getRepoId() {
    return repoId;
  }

  public Date getStatusDate() {
    return statusDate;
  }

  public String getStatus() {
    return status;
  }
}
