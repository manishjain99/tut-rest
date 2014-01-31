package com.yummynoodlebar.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.events.repos.RepoStatusEvent;
import com.yummynoodlebar.core.events.repos.RequestRepoStatusEvent;
import com.yummynoodlebar.core.services.RepoService;
import com.yummynoodlebar.rest.domain.RepoStatus;

@Controller
@RequestMapping("/api/repos/{id}/status")
public class RepoStatusController {

  @Autowired
  private RepoService repoService;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<RepoStatus> getRepoStatus(@PathVariable String id) {

    RepoStatusEvent repoStatusEvent = repoService.requestRepoStatus(new RequestRepoStatusEvent(RepoId.fromString(id)));

    if (!repoStatusEvent.isEntityFound()) {
      return new ResponseEntity<RepoStatus>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<RepoStatus>(
            RepoStatus.fromRepoStatusDetails(
                    repoStatusEvent.getKey(),
                    repoStatusEvent.getRepoStatus()),
            HttpStatus.OK);
  }
}
