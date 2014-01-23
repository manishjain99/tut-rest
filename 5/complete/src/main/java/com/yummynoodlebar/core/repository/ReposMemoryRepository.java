package com.yummynoodlebar.core.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.yummynoodlebar.core.domain.Repo;

public class ReposMemoryRepository implements RepoRepository {

  private Map<UUID, Repo> repos;

  public ReposMemoryRepository(final Map<UUID, Repo> repos) {
    this.repos = Collections.unmodifiableMap(repos);
  }

  @Override
  public synchronized Repo save(Repo repo) {

    Map<UUID, Repo> modifiableRepos = new HashMap<UUID, Repo>(repos);
    modifiableRepos.put(repo.getKey(), repo);
    this.repos = Collections.unmodifiableMap(modifiableRepos);

    return repo;
  }

  @Override
  public synchronized void delete(UUID key) {
    if (repos.containsKey(key)) {
      Map<UUID, Repo> modifiableRepos = new HashMap<UUID, Repo>(repos);
      modifiableRepos.remove(key);
      this.repos = Collections.unmodifiableMap(modifiableRepos);
    }
  }

  @Override
  public Repo findById(UUID key) {
    return repos.get(key);
  }

  @Override
  public List<Repo> findAll() {
    return Collections.unmodifiableList(new ArrayList<Repo>(repos.values()));
  }
}
