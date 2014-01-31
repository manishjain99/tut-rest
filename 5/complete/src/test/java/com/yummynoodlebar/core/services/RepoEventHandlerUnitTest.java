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

import org.junit.Before;
import org.junit.Test;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.domain.RepoCore;
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

		when(mockReposMemoryRepository.save(any(RepoCore.class))).thenReturn(
				new RepoCore());

		CreateRepoEvent ev = new CreateRepoEvent(new RepoDetails());

		uut.createRepo(ev);

		verify(mockReposMemoryRepository).save(any(RepoCore.class));
		verifyNoMoreInteractions(mockReposMemoryRepository);
	}

	@Test
	public void addTwoNewReposToTheSystem() {

		when(mockReposMemoryRepository.save(any(RepoCore.class))).thenReturn(
				new RepoCore());

		CreateRepoEvent ev = new CreateRepoEvent(new RepoDetails());

		uut.createRepo(ev);
		uut.createRepo(ev);

		verify(mockReposMemoryRepository, times(2)).save(any(RepoCore.class));
		verifyNoMoreInteractions(mockReposMemoryRepository);
	}

	@Test
	public void removeAnRepoFromTheSystemFailsIfNotPresent() {
		RepoId key = RepoId.randomRepoId();

		when(mockReposMemoryRepository.findById(key)).thenReturn(null);

	}

	@Test
	public void removeAnRepoFromTheSystemFailsIfNotPermitted() {
		RepoId key = RepoId.randomRepoId();

		RepoCore repo = new RepoCore() {

			private static final long serialVersionUID = 1L;

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

		RepoId key = RepoId.randomRepoId();
		RepoCore repo = new RepoCore();

		when(mockReposMemoryRepository.findById(key)).thenReturn(repo);

		DeleteRepoEvent ev = new DeleteRepoEvent(key);

		RepoDeletedEvent repoDeletedEvent = uut.deleteRepo(ev);

		verify(mockReposMemoryRepository).delete(ev.getKey());

		assertTrue(repoDeletedEvent.isEntityFound());
		assertTrue(repoDeletedEvent.isDeletionCompleted());
	}
}
