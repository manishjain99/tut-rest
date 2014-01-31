package com.yummynoodlebar.rest.domain;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yummynoodelbar.common.RepoCommon;
import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.events.repos.RepoDetails;

@XmlRootElement()
public class Repo extends RepoCommon {

	private static final long serialVersionUID = 1L;

	public Repo() {
	}

	public Repo(RepoDetails repoDetails) {
		BeanUtils.copyProperties(repoDetails, this);
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
