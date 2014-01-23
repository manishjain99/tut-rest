package com.yummynoodlebar.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.yummynoodlebar.core.events.repos.RepoDetails;

public class Repo {
	private final UUID key;
	private User owner;
	private List<User> contributors;
	private Language language;
	private RepoStatus status;
	private List<RepoStatus> statusHistory;
	private Visibility visibility;

	public Repo() {
		this.key = UUID.randomUUID();
		statusHistory = new ArrayList<RepoStatus>();
	}

	public void addStatus(RepoStatus newStatus) {
		statusHistory.add(newStatus);
		status = newStatus;
	}

	public RepoStatus getStatus() {
		return status;
	}
	
	public Language getLanguage(){
		return language;
	}
	
	public void setLanguage(Language language){
		this.language = language;
	}

	public UUID getKey() {
		return key;
	}


	public boolean canBeDeleted() {
		return true;
	}

	public RepoDetails toRepoDetails() {
		RepoDetails details = new RepoDetails();

		BeanUtils.copyProperties(this, details);

		return details;
	}

	public List<User> getAuthors() {
		return contributors;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	
	public Visibility getVisibility() {
		return visibility;
	}
	


	public static Repo fromRepoDetails(RepoDetails repoDetails) {
		Repo repo = new Repo();
		BeanUtils.copyProperties(repoDetails, repo);
		return repo;
	}
}
