package com.amqptest.amqptest.rabbit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TradePayModelRes<T> implements Serializable {

    private long outTradeNo;

    private T body;

}
