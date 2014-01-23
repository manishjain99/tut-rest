package com.yummynoodlebar.core.services;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.yummynoodlebar.core.domain.Repo;
import com.yummynoodlebar.core.events.repos.CreateRepoEvent;
import com.yummynoodlebar.core.events.repos.DeleteRepoEvent;
import com.yummynoodlebar.core.events.repos.RepoDeletedEvent;
import com.yummynoodlebar.core.events.repos.RepoDetails;
import com.yummynoodlebar.core.repository.ReposMemoryRepository;

public class RepoEventHandlerUnitTest {

  RepoEventHandler uut;
  ReposMemoryRepository mockReposMemoryRepository;

  @Before
  public void setupUnitUnderTest() {
    mockReposMemoryRepository = mock(ReposMemoryRepository.class);
    uut = new RepoEventHandler(mockReposMemoryRepository);
  }

  @Test
  public void addANewRepoToTheSystem() {

    when(mockReposMemoryRepository.save(any(Repo.class))).thenReturn(new Repo());

    CreateRepoEvent ev = new CreateRepoEvent(new RepoDetails());

    uut.createRepo(ev);

    verify(mockReposMemoryRepository).save(any(Repo.class));
    verifyNoMoreInteractions(mockReposMemoryRepository);
  }

  @Test
  public void addTwoNewReposToTheSystem() {

    when(mockReposMemoryRepository.save(any(Repo.class))).thenReturn(new Repo());

    CreateRepoEvent ev = new CreateRepoEvent(new RepoDetails());

    uut.createRepo(ev);
    uut.createRepo(ev);

    verify(mockReposMemoryRepository, times(2)).save(any(Repo.class));
    verifyNoMoreInteractions(mockReposMemoryRepository);
  }

  @Test
  public void removeAnRepoFromTheSystemFailsIfNotPresent() {
    UUID key = UUID.randomUUID();

    when(mockReposMemoryRepository.findById(key)).thenReturn(null);


  }

  @Test
  public void removeAnRepoFromTheSystemFailsIfNotPermitted() {
    UUID key = UUID.randomUUID();

    Repo repo = new Repo() {
      @Override
      public boolean canBeDeleted() {
        return false;
      }
    };

    when(mockReposMemoryRepository.findById(key)).thenReturn(repo);

    DeleteRepoEvent ev = new DeleteRepoEvent(key);

    RepoDeletedEvent repoDeletedEvent = uut.deleteRepo(ev);

    verify(mockReposMemoryRepository, never()).delete(ev.getKey());

    assertTrue(repoDeletedEvent.isEntityFound());
    assertFalse(repoDeletedEvent.isDeletionCompleted());
  }

  @Test
  public void removeAnRepoFromTheSystemWorksIfExists() {

    UUID key = UUID.randomUUID();
    Repo repo = new Repo();

    when(mockReposMemoryRepository.findById(key)).thenReturn(repo);

    DeleteRepoEvent ev = new DeleteRepoEvent(key);

    RepoDeletedEvent repoDeletedEvent = uut.deleteRepo(ev);

    verify(mockReposMemoryRepository).delete(ev.getKey());

    assertTrue(repoDeletedEvent.isEntityFound());
    assertTrue(repoDeletedEvent.isDeletionCompleted());
  }
}
