package com.order.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Order entity
 */
@Entity
@Table(name = "Orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL generated value
  private Long id;

  private double tax;

  private double total;

  private String status;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
  private List<LineItem> lineItems;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @Column(name = "date_time_created", nullable = false, updatable=false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;

  @Column(name = "date_time_ready", nullable = false, updatable=true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date ready;

  @Column(name = "date_time_pickedup", nullable = false, updatable=true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date pickedUp;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public double getTax() {
    return tax;
  }

  public void setTax(double tax) {
    this.tax = tax;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getReady() {
    return ready;
  }

  public void setReady(Date ready) {
    this.ready = ready;
  }

  public Date getPickedUp() {
    return pickedUp;
  }

  public void setPickedUp(Date pickedUp) {
    this.pickedUp = pickedUp;
  }

  public List<LineItem> getLineItems() {
    return lineItems;
  }

  public void setLineItems(List<LineItem> lineItems) {
    this.lineItems = lineItems;
  }
}
