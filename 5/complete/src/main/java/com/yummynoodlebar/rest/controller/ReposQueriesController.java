package com.yummynoodlebar.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.events.repos.RepoDetails;
import com.yummynoodlebar.core.events.repos.RepoDetailsEvent;
import com.yummynoodlebar.core.events.repos.RequestAllReposEvent;
import com.yummynoodlebar.core.events.repos.RequestRepoDetailsEvent;
import com.yummynoodlebar.core.services.RepoService;
import com.yummynoodlebar.rest.domain.Repo;

@Controller
@RequestMapping("/api/repos")
public class ReposQueriesController {

	@Autowired
	private RepoService repoService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Repo> getAllRepos() {
		List<Repo> repos = new ArrayList<Repo>();
		for (RepoDetails detail : repoService.requestAllRepos(
				new RequestAllReposEvent()).getReposDetails()) {
			repos.add(new Repo(detail));
		}
		return repos;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Repo> viewRepo(@PathVariable String id) {

		RepoDetailsEvent details = repoService
				.requestRepoDetails(new RequestRepoDetailsEvent(RepoId
						.fromString(id)));

		if (!details.isEntityFound()) {
			return new ResponseEntity<Repo>(HttpStatus.NOT_FOUND);
		}

		Repo repo = new Repo(details.getRepoDetails());

		return new ResponseEntity<Repo>(repo, HttpStatus.OK);
	}
}
