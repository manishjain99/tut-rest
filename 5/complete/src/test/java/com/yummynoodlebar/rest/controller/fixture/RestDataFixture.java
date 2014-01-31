package com.yummynoodlebar.rest.controller.fixture;

import java.util.ArrayList;
import java.util.List;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.events.repos.AllReposEvent;
import com.yummynoodlebar.core.events.repos.RepoDetails;
import com.yummynoodlebar.rest.domain.Repo;

//TODOCUMENT.  Use of test data fixture classes is considered good practice.
/*
 The majority of tests aren't testing data edge cases, they are testing logical flows and
 what happens to a generic set of data.  For these, use a small, standardised set of fixtures.

 For anything more esoteric, create a new fixture in the test class.
 */
public class RestDataFixture {

	public static AllReposEvent allRepos() {
		List<RepoDetails> repos = new ArrayList<RepoDetails>();

		repos.add(standardRepoDetails());
		repos.add(standardRepoDetails());
		repos.add(standardRepoDetails());

		return new AllReposEvent(repos);
	}

	public static Repo standardRepo() {
		Repo repo = new Repo();

		return repo;
	}

	public static RepoDetails customKeyRepoDetails(RepoId key) {
		RepoDetails repodetails = new RepoDetails();
		repodetails.setId(key.get());
		return repodetails;
	}

	public static RepoDetails standardRepoDetails() {
		return customKeyRepoDetails(new RepoId(444L));// created 444 random
	}

	public static String standardRepoJSON() {
		return "{ \"language\":  \"java\"}";
	}
}
