package com.yummynoodlebar.rest.domain;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.events.repos.RepoDetails;

@XmlRootElement()
public class CopyOfRepo  {

	public CopyOfRepo() {
	}

	public CopyOfRepo(RepoDetails repoDetails) {
		BeanUtils.copyProperties(repoDetails, this);
	}
	
	public int getId(){
		return 0;
	}

	@JsonIgnore
	public RepoId getRepoId() {
		return new RepoId(getId());
	}

	@JsonIgnore
	public RepoDetails toRepoDetails() {
		RepoDetails details = new RepoDetails();
		return details;
	}

}
