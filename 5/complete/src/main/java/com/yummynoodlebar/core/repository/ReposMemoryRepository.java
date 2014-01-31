package com.yummynoodlebar.core.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.domain.RepoCore;

public class ReposMemoryRepository implements RepoRepository {

  private Map<RepoId, RepoCore> repos;

  public ReposMemoryRepository(final Map<RepoId, RepoCore> repos) {
    this.repos = Collections.unmodifiableMap(repos);
  }

  @Override
  public synchronized RepoCore save(RepoCore repo) {

    Map<RepoId, RepoCore> modifiableRepos = new HashMap<RepoId, RepoCore>(repos);
    modifiableRepos.put(repo.getRepoId(), repo);
    this.repos = Collections.unmodifiableMap(modifiableRepos);

    return repo;
  }

  @Override
  public synchronized void delete(RepoId key) {
    if (repos.containsKey(key)) {
      Map<RepoId, RepoCore> modifiableRepos = new HashMap<RepoId, RepoCore>(repos);
      modifiableRepos.remove(key);
      this.repos = Collections.unmodifiableMap(modifiableRepos);
    }
  }

  @Override
  public RepoCore findById(RepoId key) {
    return repos.get(key);
  }

  @Override
  public List<RepoCore> findAll() {
    return Collections.unmodifiableList(new ArrayList<RepoCore>(repos.values()));
  }
}
