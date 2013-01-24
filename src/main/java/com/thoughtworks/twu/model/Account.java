package com.thoughtworks.twu.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//postgres wont let you create table_name 'user'
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue
    private Long account_id;

    @NotNull
    private String account_name;

    @NotNull
    private String password;

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

}
