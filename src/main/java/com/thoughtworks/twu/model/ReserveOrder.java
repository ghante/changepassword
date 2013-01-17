package com.thoughtworks.twu.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "reserve_order")
public class ReserveOrder {

    @Id
    @GeneratedValue
    private Long order_id;

    @NotNull
    private Long account_id;

    @NotNull
    private Long item_id;


    @Column(name="reservation_timestamp", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservation_timestamp;

    public ReserveOrder(){}

    public ReserveOrder(Long account_id, Long item_id, Date rightNow) {
        this.account_id = account_id;
        this.item_id = item_id;
        this.reservation_timestamp = rightNow;
    }

    @PrePersist
    protected void onCreate() {
        reservation_timestamp = new Date();
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public Date getReservation_timestamp() {
        return reservation_timestamp;
    }

    public void setReservation_timestamp(Date reservation_timestamp) {
        this.reservation_timestamp = reservation_timestamp;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

}
