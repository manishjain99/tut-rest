package com.yummynoodlebar.core.domain;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.yummynoodlebar.core.domain.fixtures.RepoFixtures;
import com.yummynoodlebar.core.repository.ReposMemoryRepository;

public class ReposUnitTest {

  ReposMemoryRepository uut;

  @Before
  public void setupUnitUnderTest() {
    Map<UUID, Repo> emptyRepoList = new HashMap<UUID, Repo>();
    uut = new ReposMemoryRepository(emptyRepoList);
  }

  @Test
  public void addASingleRepoToTheRepos() {

    assertEquals(0, uut.findAll().size());

    uut.save(RepoFixtures.standardRepo());

    assertEquals(1, uut.findAll().size());
  }

  @Test
  public void removeASingleRepo() {

    UUID key = UUID.randomUUID();

    uut = new ReposMemoryRepository(Collections.singletonMap(key, RepoFixtures.standardRepo()));

    assertEquals(1, uut.findAll().size());

    uut.delete(key);

    assertEquals(0, uut.findAll().size());
  }
}
