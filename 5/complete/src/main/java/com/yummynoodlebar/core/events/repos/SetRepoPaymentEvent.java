package com.yummynoodlebar.core.events.repos;

import com.yummynoodelbar.common.RepoId;
import com.yummynoodlebar.core.events.UpdateEvent;

public class SetRepoPaymentEvent extends UpdateEvent {

  private RepoId key;
  private PaymentDetails paymentDetails;

  public SetRepoPaymentEvent(RepoId key, PaymentDetails paymentDetails) {
    this.key = key;
    this.paymentDetails = paymentDetails;
  }

  public RepoId getKey() {
    return key;
  }

  public PaymentDetails getPaymentDetails() {
    return paymentDetails;
  }
}
