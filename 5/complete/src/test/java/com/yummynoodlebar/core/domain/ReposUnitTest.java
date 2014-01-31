package com.yummynoodlebar.core.domain;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.domain.fixtures.RepoFixtures;
import com.yummynoodlebar.core.repository.ReposMemoryRepository;

public class ReposUnitTest {

  ReposMemoryRepository uut;

  @Before
  public void setupUnitUnderTest() {
    Map<RepoId, RepoCore> emptyRepoList = new HashMap<RepoId, RepoCore>();
    uut = new ReposMemoryRepository(emptyRepoList);
  }

  @Test
  public void addASingleRepoToTheRepos() {

    assertEquals(0, uut.findAll().size());

    uut.save(RepoFixtures.createStandardRepo());

    assertEquals(1, uut.findAll().size());
  }

  @Test
  public void removeASingleRepo() {

    RepoId key = RepoId.randomRepoId();

    uut = new ReposMemoryRepository(Collections.singletonMap(key, RepoFixtures.createStandardRepo()));

    assertEquals(1, uut.findAll().size());

    uut.delete(key);

    assertEquals(0, uut.findAll().size());
  }
}
