package com.yummynoodlebar.core.repository;

import java.util.List;
import java.util.UUID;

import com.yummynoodlebar.core.domain.Repo;

//TODO, make this event based again, with persistence integration events.
public interface RepoRepository {

  Repo save(Repo repo);

  void delete(UUID key);

  Repo findById(UUID key);

  List<Repo> findAll();
}
