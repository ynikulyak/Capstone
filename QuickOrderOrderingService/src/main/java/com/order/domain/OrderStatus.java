package com.order.domain;

/**
 * Possible order statuses.
 */
public enum OrderStatus {
  UNKNOWN, PAID_AND_SCHEDULED, ASSIGNED, PROCESSING, COMPLETED, CANCELLED;
}
