package com.yummynoodlebar.rest.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.yummynoodlebar.core.events.repos.CreateRepoEvent;
import com.yummynoodlebar.core.events.repos.DeleteRepoEvent;
import com.yummynoodlebar.core.events.repos.RepoCreatedEvent;
import com.yummynoodlebar.core.events.repos.RepoDeletedEvent;
import com.yummynoodlebar.core.services.RepoService;
import com.yummynoodlebar.rest.domain.Repo;

@Controller
@RequestMapping("/aggregators/repos")
public class RepositoryCommandsController {

    private static Logger LOG = LoggerFactory.getLogger(RepositoryCommandsController.class);

    @Autowired
    private RepoService repoService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Repo> createRepo(@RequestBody Repo repo, UriComponentsBuilder builder) {

        RepoCreatedEvent repoCreated = repoService.createRepo(new CreateRepoEvent(repo.toRepoDetails()));

        Repo newRepo = Repo.fromRepoDetails(repoCreated.getDetails());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/aggregators/repos/{id}")
                        .buildAndExpand(repoCreated.getNewRepoKey().toString()).toUri());

        return new ResponseEntity<Repo>(newRepo, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Repo> cancelRepo(@PathVariable String id) {

        RepoDeletedEvent repoDeleted = repoService.deleteRepo(new DeleteRepoEvent(UUID.fromString(id)));

        if (!repoDeleted.isEntityFound()) {
            return new ResponseEntity<Repo>(HttpStatus.NOT_FOUND);
        }

        Repo repo = Repo.fromRepoDetails(repoDeleted.getDetails());

        if (repoDeleted.isDeletionCompleted()) {
            return new ResponseEntity<Repo>(repo, HttpStatus.OK);
        }

        return new ResponseEntity<Repo>(repo, HttpStatus.FORBIDDEN);
    }
}
