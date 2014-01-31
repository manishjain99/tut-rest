package com.yummynoodlebar.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.events.repos.CreateRepoEvent;
import com.yummynoodlebar.core.events.repos.DeleteRepoEvent;
import com.yummynoodlebar.core.events.repos.RepoCreatedEvent;
import com.yummynoodlebar.core.events.repos.RepoDeletedEvent;
import com.yummynoodlebar.core.services.RepoService;
import com.yummynoodlebar.rest.domain.CopyOfRepo;
import com.yummynoodlebar.rest.domain.Repo;

@Controller
@RequestMapping("/api/repos")
public class RepositoryCommandsController {

	@Autowired
	private RepoService repoService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Repo> createRepo(@RequestBody Repo repo,
			UriComponentsBuilder builder) {

		RepoCreatedEvent repoCreated = null;
		repoCreated = repoService.createRepo(new CreateRepoEvent(repo.toRepoDetails()));
		Repo newRepo = new Repo(repoCreated.getDetails());

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/api/repos/{id}")
				.buildAndExpand(repoCreated.getNewRepoKey()).toUri());

		return new ResponseEntity<Repo>(newRepo, new HttpHeaders(), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Repo> cancelRepo(@PathVariable String id) {

		RepoDeletedEvent repoDeleted = repoService
				.deleteRepo(new DeleteRepoEvent(RepoId.fromString(id)));

		if (!repoDeleted.isEntityFound()) {
			return new ResponseEntity<Repo>(HttpStatus.NOT_FOUND);
		}

		Repo repo = new Repo(repoDeleted.getDetails());

		if (repoDeleted.isDeletionCompleted()) {
			return new ResponseEntity<Repo>(repo, HttpStatus.OK);
		}

		return new ResponseEntity<Repo>(repo, HttpStatus.FORBIDDEN);
	}
}
