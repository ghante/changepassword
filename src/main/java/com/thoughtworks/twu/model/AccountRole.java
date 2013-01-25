package com.thoughtworks.twu.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "AccountRole")
public class AccountRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;

    //TODO: Apply foriegn key contraint from Account.account_name
    @NotNull
    private String account_name;

    @NotNull
    private String role;

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
