package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Item;

import java.util.Date;

public class ReservedOrderDetail  {

    Item item;
    Account account;
    Date reserve_time;

    public ReservedOrderDetail(Account account, Item item, Date reserve_time){
        this.item = item;
        this.account = account;
        this.reserve_time = reserve_time;
    }

    public ReservedOrderDetail() {
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getReserve_time() {
        return reserve_time;
    }

    public void setReserve_time(Date reserve_time) {
        this.reserve_time = reserve_time;
    }

}
