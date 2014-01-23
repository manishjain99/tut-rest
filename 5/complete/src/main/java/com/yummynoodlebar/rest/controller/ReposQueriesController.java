package com.yummynoodlebar.rest.controller;

import com.yummynoodlebar.core.events.repos.*;
import com.yummynoodlebar.core.services.RepoService;
import com.yummynoodlebar.rest.domain.Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/aggregators/repos")
public class ReposQueriesController {

    private static Logger LOG = LoggerFactory.getLogger(ReposQueriesController.class);

    @Autowired
    private RepoService repoService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Repo> getAllRepos() {
        List<Repo> repos = new ArrayList<Repo>();
        for (RepoDetails detail : repoService.requestAllRepos(new RequestAllReposEvent()).getReposDetails()) {
            repos.add(Repo.fromRepoDetails(detail));
        }
        return repos;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Repo> viewRepo(@PathVariable String id) {

        RepoDetailsEvent details = repoService.requestRepoDetails(new RequestRepoDetailsEvent(UUID.fromString(id)));

        if (!details.isEntityFound()) {
            return new ResponseEntity<Repo>(HttpStatus.NOT_FOUND);
        }

        Repo repo = Repo.fromRepoDetails(details.getRepoDetails());

        return new ResponseEntity<Repo>(repo, HttpStatus.OK);
    }
}
