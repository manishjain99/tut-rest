package com.yummynoodlebar.core.events.repos;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.yummynoodlebar.core.domain.User;

public class RepoDetails {

	private UUID key;
	private Date dateTimeOfSubmission;
	private Map<String, Integer> repoItems;
	private User owner;

	public RepoDetails() {
		key = null;
	}

	public RepoDetails(UUID key) {
		this.key = key;
	}

	public Date getDateTimeOfSubmission() {
		return this.dateTimeOfSubmission;
	}

	public User getOwner() {
		return owner;
	}

	public void setDateTimeOfSubmission(Date dateTimeOfSubmission) {
		this.dateTimeOfSubmission = dateTimeOfSubmission;
	}

	public Map<String, Integer> getRepoItems() {
		return repoItems;
	}

	public void setRepoItems(Map<String, Integer> repoItems) {
		if (repoItems == null) {
			this.repoItems = Collections.emptyMap();
		} else {
			this.repoItems = Collections.unmodifiableMap(repoItems);
		}
	}

	public UUID getKey() {
		return key;
	}

	public void setKey(UUID key) {
		this.key = key;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
