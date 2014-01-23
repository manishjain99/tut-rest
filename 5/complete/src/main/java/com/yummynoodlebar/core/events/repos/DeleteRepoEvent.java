package com.yummynoodlebar.core.events.repos;

import com.yummynoodlebar.core.events.DeleteEvent;

import java.util.UUID;

public class DeleteRepoEvent extends DeleteEvent {

  private final UUID key;

  public DeleteRepoEvent(final UUID key) {
    this.key = key;
  }

  public UUID getKey() {
    return key;
  }
}
