package com.yummynoodlebar.core.services;

import com.yummynoodlebar.core.events.repos.*;

//TODOCUMENT THis is an event driven service.
// Used to interact with the core domain.
//All methods are guaranteed to return something, null will never be returned.
public interface RepoService {

  public AllReposEvent requestAllRepos(RequestAllReposEvent requestAllCurrentReposEvent);

  public RepoDetailsEvent requestRepoDetails(RequestRepoDetailsEvent requestRepoDetailsEvent);

  public RepoStatusEvent requestRepoStatus(RequestRepoStatusEvent requestRepoStatusEvent);

  public RepoCreatedEvent createRepo(CreateRepoEvent event);

  public RepoUpdatedEvent setRepoPayment(SetRepoPaymentEvent setRepoPaymentEvent);

  public RepoDeletedEvent deleteRepo(DeleteRepoEvent deleteRepoEvent);
}
