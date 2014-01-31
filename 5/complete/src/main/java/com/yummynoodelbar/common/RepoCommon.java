package com.yummynoodelbar.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yummynoodelbar.common.Visibility;
import com.yummynoodlebar.core.events.repos.RepoDetails;

public class RepoCommon extends Repository {
	private static final long serialVersionUID = 1L;
	private List<RepoStatus> statusHistory;
	private Visibility visibility;

	public RepoCommon() {
		statusHistory = new ArrayList<RepoStatus>();
	}
	public RepoCommon(RepoDetails repoDetails) {
		BeanUtils.copyProperties(repoDetails, this);
	}
	@JsonIgnore
	public RepoId getRepoId(){
		return new RepoId(getId());
	}
	@JsonIgnore
	public void setStatus(RepoStatus newStatus) {
		statusHistory.add(newStatus);
	}
	@JsonIgnore
	public RepoStatus getStatus() {
		if(statusHistory.size()==0) return new RepoStatus(new Date(), "Status Not Set Yet");
		return statusHistory.get(statusHistory.size()-1);
	}
	@JsonIgnore
	public RepoDetails toRepoDetails() {
		RepoDetails details = new RepoDetails();
		BeanUtils.copyProperties(this, details);
		return details;
	}

	@JsonIgnore
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	@JsonIgnore
	public Visibility getVisibility() {
		return visibility;
	}

	public boolean canBeDeleted() {
		return true;

	}
}
