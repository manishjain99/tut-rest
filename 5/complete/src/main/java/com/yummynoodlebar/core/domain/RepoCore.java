package com.yummynoodlebar.core.domain;

import org.springframework.beans.BeanUtils;

import com.yummynoodelbar.common.RepoCommon;
import com.yummynoodlebar.core.events.repos.RepoDetails;

public class RepoCore extends RepoCommon {
	private static final long serialVersionUID = 875167051440412739L;
	public RepoCore(){
		super();
	}

	public RepoCore(RepoDetails repoDetails) {
		super();
		BeanUtils.copyProperties(repoDetails, this);
	}

}
