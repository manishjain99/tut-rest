package com.yummynoodlebar.core.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yummynoodlebar.core.domain.Repo;
import com.yummynoodlebar.core.domain.RepoStatus;
import com.yummynoodlebar.core.events.repos.AllReposEvent;
import com.yummynoodlebar.core.events.repos.CreateRepoEvent;
import com.yummynoodlebar.core.events.repos.DeleteRepoEvent;
import com.yummynoodlebar.core.events.repos.RepoUpdatedEvent;
import com.yummynoodlebar.core.events.repos.RepoCreatedEvent;
import com.yummynoodlebar.core.events.repos.RepoDeletedEvent;
import com.yummynoodlebar.core.events.repos.RepoDetails;
import com.yummynoodlebar.core.events.repos.RepoDetailsEvent;
import com.yummynoodlebar.core.events.repos.RepoStatusEvent;
import com.yummynoodlebar.core.events.repos.RequestAllReposEvent;
import com.yummynoodlebar.core.events.repos.RequestRepoDetailsEvent;
import com.yummynoodlebar.core.events.repos.RequestRepoStatusEvent;
import com.yummynoodlebar.core.events.repos.SetRepoPaymentEvent;
import com.yummynoodlebar.core.repository.RepoRepository;

public class RepoEventHandler implements RepoService {

  private final RepoRepository reposRepository;

  public RepoEventHandler(final RepoRepository reposRepository) {
    this.reposRepository = reposRepository;
  }

  @Override
  public RepoCreatedEvent createRepo(CreateRepoEvent createRepoEvent) {
    Repo repo = Repo.fromRepoDetails(createRepoEvent.getDetails());

    repo.addStatus(new RepoStatus(new Date(), "Repo Created"));

    repo = reposRepository.save(repo);

    return new RepoCreatedEvent(repo.getKey(), repo.toRepoDetails());
  }

  @Override
  public AllReposEvent requestAllRepos(RequestAllReposEvent requestAllCurrentReposEvent) {
    List<RepoDetails> generatedDetails = new ArrayList<RepoDetails>();
    for (Repo repo : reposRepository.findAll()) {
      generatedDetails.add(repo.toRepoDetails());
    }
    return new AllReposEvent(generatedDetails);
  }

  @Override
  public RepoDetailsEvent requestRepoDetails(RequestRepoDetailsEvent requestRepoDetailsEvent) {

    Repo repo = reposRepository.findById(requestRepoDetailsEvent.getKey());

    if (repo == null) {
      return RepoDetailsEvent.notFound(requestRepoDetailsEvent.getKey());
    }

    return new RepoDetailsEvent(
            requestRepoDetailsEvent.getKey(),
            repo.toRepoDetails());
  }

  @Override
  public RepoUpdatedEvent setRepoPayment(SetRepoPaymentEvent setRepoPaymentEvent) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public RepoDeletedEvent deleteRepo(DeleteRepoEvent deleteRepoEvent) {

    Repo repo = reposRepository.findById(deleteRepoEvent.getKey());

    if (repo == null) {
      return RepoDeletedEvent.notFound(deleteRepoEvent.getKey());
    }

    RepoDetails details = repo.toRepoDetails();

    //TODOCUMENT This contains some specific domain logic, not exposed to the outside world, and not part of the
    //persistence rules.

    if (!repo.canBeDeleted()) {
      return RepoDeletedEvent.deletionForbidden(deleteRepoEvent.getKey(), details);
    }

    reposRepository.delete(deleteRepoEvent.getKey());
    return new RepoDeletedEvent(deleteRepoEvent.getKey(), details);
  }

  @Override
  public RepoStatusEvent requestRepoStatus(RequestRepoStatusEvent requestRepoDetailsEvent) {
    Repo repo = reposRepository.findById(requestRepoDetailsEvent.getKey());

    if (repo == null) {
      return RepoStatusEvent.notFound(requestRepoDetailsEvent.getKey());
    }

    return new RepoStatusEvent(requestRepoDetailsEvent.getKey(), repo.getStatus().toStatusDetails());
  }
}
