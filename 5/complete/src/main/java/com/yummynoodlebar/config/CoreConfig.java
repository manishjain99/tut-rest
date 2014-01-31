package com.yummynoodlebar.config;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.domain.RepoCore;
import com.yummynoodlebar.core.repository.RepoRepository;
import com.yummynoodlebar.core.repository.ReposMemoryRepository;
import com.yummynoodlebar.core.services.RepoEventHandler;
import com.yummynoodlebar.core.services.RepoService;

@Configuration
public class CoreConfig {


  @Bean
  public RepoService createService(RepoRepository repo) {
    return new RepoEventHandler(repo);
  }

  @Bean
  public RepoRepository createRepo() {
    return new ReposMemoryRepository(new HashMap<RepoId, RepoCore>());
  }

}
