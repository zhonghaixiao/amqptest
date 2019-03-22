package com.amqptest.amqptest.first.controller;

import com.amqptest.amqptest.first.dao.AccountRepository;
import com.amqptest.amqptest.first.domain.Account;
import com.amqptest.amqptest.rabbit.RabbitSender;
import com.amqptest.amqptest.rabbit.TradePayModelRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subsystem")
public class SubsystemAuthorityService
{
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RabbitSender sender;

    @GetMapping("/admin/info")
    public String getAdminInfo(String currentAccount)
    {
        Account account = accountRepository.findOneByAccount(currentAccount);
        System.out.println(account);
        return account.toString();
    }

    @PostMapping("/account/new")
    public Object createAccount(@RequestBody Account account){
        return accountRepository.save(account);
    }

    @PostMapping("send")
    public Object seedMsg(){
        sender.sendIngateQueue(TradePayModelRes.builder().outTradeNo(123).body("test msg").build());
        return true;
    }

}