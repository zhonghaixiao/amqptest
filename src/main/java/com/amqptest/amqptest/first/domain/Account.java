package com.amqptest.amqptest.first.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Account
{
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    private String account;
    @Column(name = "call_phone")
    private String phone;
    @Column(name = "nick_name")
    private String nickname;
    private String password;
}