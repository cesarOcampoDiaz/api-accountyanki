package com.nttdata.message;

import com.nttdata.document.Transaction;
import reactor.core.publisher.Mono;

public interface KafkaConsumer {

     Mono<Transaction> getTransaction();
}
