package com.yummynoodelbar.common;


public class RepoId extends Id<Long> {
	public RepoId(long id){
		super(id);
	}
	
	public static RepoId fromString(String src) {
		return new RepoId(Long.parseLong(src));
	}

	public static RepoId randomRepoId() {
		return new RepoId((long) Math.random()*240000);

	}

}
