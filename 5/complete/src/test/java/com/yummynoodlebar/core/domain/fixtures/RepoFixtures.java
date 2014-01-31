package com.yummynoodlebar.core.domain.fixtures;

import org.eclipse.egit.github.core.User;

import com.yummynoodlebar.core.domain.RepoCore;
import com.yummynoodlebar.core.events.repos.RepoDetails;

public class RepoFixtures {

  public static RepoCore createStandardRepo() {
    RepoCore repo = new RepoCore();
    repo.setOwner(createStandardUser());
    return repo;
  }

  /*
   * Twin of the above, to improve readability
   */
  public static RepoDetails createStandardRepoDetails() {
    return createStandardRepo().toRepoDetails();
  }
  public static User createStandardUser(){
	  User owner = new User();
	  owner.setId(22);
	  owner.setName("Manish");
	  owner.setEmail("mjain@ebay.com");
	  return owner;
  }

}
