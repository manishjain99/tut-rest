package com.yummynoodlebar.core.events.repos;

import com.yummynoodlebar.core.events.RequestReadEvent;

import java.util.UUID;

public class RequestRepoDetailsEvent extends RequestReadEvent {
  private UUID key;

  public RequestRepoDetailsEvent(UUID key) {
    this.key = key;
  }

  public UUID getKey() {
    return key;
  }
}
