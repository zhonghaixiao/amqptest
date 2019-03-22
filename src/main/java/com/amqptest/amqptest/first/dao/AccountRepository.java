package com.amqptest.amqptest.first.dao;

import com.amqptest.amqptest.first.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

    Account findOneByAccount(String account);

}
