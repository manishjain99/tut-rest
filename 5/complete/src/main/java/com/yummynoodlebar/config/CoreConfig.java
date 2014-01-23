package com.yummynoodlebar.config;

import com.yummynoodlebar.core.domain.Repo;
import com.yummynoodlebar.core.repository.ReposMemoryRepository;
import com.yummynoodlebar.core.repository.RepoRepository;
import com.yummynoodlebar.core.services.RepoEventHandler;
import com.yummynoodlebar.core.services.RepoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.UUID;

@Configuration
public class CoreConfig {


  @Bean
  public RepoService createService(RepoRepository repo) {
    return new RepoEventHandler(repo);
  }

  @Bean
  public RepoRepository createRepo() {
    return new ReposMemoryRepository(new HashMap<UUID, Repo>());
  }

}
