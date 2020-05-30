package com.order.rest;

/**
 * Rest entity for size.
 */
public class SizeDto {

  private long id;
  private String volume;

  public SizeDto() {
  }

  public SizeDto(long id, String volume) {
    super();
    this.id = id;
    this.volume = volume;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getVolume() {
    return volume;
  }

  public void setVolume(String volume) {
    this.volume = volume;
  }

  @Override
  public String toString() {
    return "Sizes [id=" + id + ", volume=" + volume + "]";
  }
}
