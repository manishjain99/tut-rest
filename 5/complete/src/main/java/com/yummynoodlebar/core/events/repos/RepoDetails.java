package com.yummynoodlebar.core.events.repos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.eclipse.egit.github.core.Repository;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodelbar.common.RepoStatus;

public class RepoDetails extends Repository {
	private static final long serialVersionUID = 1L;
	private List<RepoStatus> statusHistory = new ArrayList<RepoStatus>();
	
	public RepoId getRepoId(){
		return new RepoId(getId());
	}
	public void setStatus(RepoStatus status){
		statusHistory.add(status);
	}
	public RepoStatus getStatus(){
		if(statusHistory.size() ==0) return new RepoStatus(new Date(), "No Status set yet");
		RepoStatus lastStatus = statusHistory.get(statusHistory.size()-1);
		return new RepoStatus(new Date(),lastStatus.getStatus());
	}
	public List<RepoStatus> getStatusHistory() {
		return Collections.unmodifiableList(statusHistory);

	}
}
