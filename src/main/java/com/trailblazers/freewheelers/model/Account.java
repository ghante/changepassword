package com.trailblazers.freewheelers.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//postgres wont let you create table_name 'user'
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;

    @NotNull
    private String account_name;

    @NotNull
    private String password;

    @NotNull
    private boolean enabled;
    private String emailAddress;
    private String phoneNumber;

    public Account() {
        this.account_id = 0L;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
