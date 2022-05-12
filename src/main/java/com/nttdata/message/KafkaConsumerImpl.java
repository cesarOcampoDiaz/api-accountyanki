package com.nttdata.message;


import com.nttdata.document.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class KafkaConsumerImpl implements  KafkaConsumer{

    public static Mono<Transaction> tran;

   @KafkaListener(topics = "trasanctionYanki",groupId = "myGroup")
   public void listenTopic(Transaction transaction){
       System.out.println(" llego");
       tran=Mono.just(transaction);

   }

    @Override
    public Mono<Transaction> getTransaction() {
        System.out.println("Entro getTransaction");
       return tran;
    }


}
