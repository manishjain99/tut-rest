package com.yummynoodlebar.rest.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import com.yummynoodlebar.core.domain.User;
import com.yummynoodlebar.core.events.repos.RepoDetails;

//TODOCUMENT This is added so that we can do jaxb serialisation.
//this type of annotation is fine here, as this
//Repo implementation is made for integration with things like this.

@XmlRootElement
public class Repo implements Serializable {

  private Date dateTimeOfSubmission;

  private Map<String, Integer> items;

  private UUID key;
  
  private User owner;

  public Date getDateTimeOfSubmission() {
    return dateTimeOfSubmission;
  }

  public UUID getKey() {
    return key;
  }

  public Map<String, Integer> getItems() {
    return items;
  }

  public void setItems(Map<String, Integer> items) {
    if (items == null) {
      this.items = Collections.emptyMap();
    } else {
      this.items = Collections.unmodifiableMap(items);
    }
  }

  public void setDateTimeOfSubmission(Date dateTimeOfSubmission) {
    this.dateTimeOfSubmission = dateTimeOfSubmission;
  }

  public void setKey(UUID key) {
    this.key = key;
  }

  public RepoDetails toRepoDetails() {
    RepoDetails details = new RepoDetails();
    details.setOwner(owner);
    details.setRepoItems(items);
    details.setKey(key);
    details.setDateTimeOfSubmission(dateTimeOfSubmission);

    return details;
  }

  public static Repo fromRepoDetails(RepoDetails repoDetails) {
    Repo repo = new Repo();

    repo.dateTimeOfSubmission = repoDetails.getDateTimeOfSubmission();
    repo.key = repoDetails.getKey();
    repo.setItems(repoDetails.getRepoItems());

    return repo;
  }
}
