package com.yummynoodlebar.core.repository;

import java.util.List;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.domain.RepoCore;

//TODO, make this event based again, with persistence integration events.
public interface RepoRepository {

  RepoCore save(RepoCore repo);

  void delete(RepoId key);

  RepoCore findById(RepoId key);

  List<RepoCore> findAll();
}
