package com.yummynoodlebar.core.events.repos;

import com.yummynoodlebar.core.events.RequestReadEvent;

import java.util.UUID;

public class RequestRepoStatusEvent extends RequestReadEvent {
  private UUID key;

  public RequestRepoStatusEvent(UUID key) {
    this.key = key;
  }

  public UUID getKey() {
    return key;
  }
}
