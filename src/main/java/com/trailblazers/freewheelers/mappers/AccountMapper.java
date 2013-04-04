package com.trailblazers.freewheelers.mappers;

import com.trailblazers.freewheelers.model.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface AccountMapper {

    @Insert(
        "INSERT INTO account (account_name, email_address, password, phone_number, enabled) " +
        "VALUES (#{account_name}, #{emailAddress}, #{password}, #{phoneNumber}, #{enabled})"
    )
    @Options(keyProperty = "account_id", useGeneratedKeys = true)
    Integer insert(Account account);

    @Select(
        "SELECT account_id, account_name, email_address, password, phone_number, enabled " +
        "FROM account " +
        "WHERE account_id = #{account_id}"
    )
    Account getById(Long account_id);

    @Select(
        "SELECT account_id, account_name, email_address, password, phone_number, enabled " +
        "FROM account " +
        "WHERE account_name = #{account_name} " +
        "LIMIT 1"
    )
    Account getByName(String accountName);
//
//    List<Account> findAll();
//
//    Account save(Account account);
//
//    void delete(Account account);

}
