package com.yummynoodlebar.core.domain.fixtures;

import com.yummynoodlebar.core.domain.User;
import com.yummynoodlebar.core.domain.Repo;
import com.yummynoodlebar.core.events.repos.RepoDetails;

public class RepoFixtures {

  public static final String YUMMY_ITEM = "yummy_core";

  public static Repo standardRepo() {
    Repo repo = new Repo();
    return repo;
  }

  /*
   * Twin of the above, to improve readability
   */
  public static RepoDetails standardRepoDetails() {
    return standardRepo().toRepoDetails();
  }
  public static User createStandardOwner(){
	  User owner = new User();
	  owner.setId(22);
	  owner.setName("Manish");
	  owner.setEmail("mjain@ebay.com");
	  return owner;
  }

}
