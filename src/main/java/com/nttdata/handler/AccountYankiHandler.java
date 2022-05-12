package com.nttdata.handler;

import com.nttdata.document.AccountYanki;
import com.nttdata.document.Transaction;
import com.nttdata.message.KafkaConsumer;
import com.nttdata.repository.AccountYankiRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class AccountYankiHandler {

    private final AccountYankiRepository accountRepository;
    @Autowired
    private KafkaConsumer kafkaConsumer;

    static Mono<ServerResponse> notFound = ServerResponse.notFound().build();



    @Autowired
    public AccountYankiHandler(AccountYankiRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Mono<ServerResponse> add(ServerRequest serverRequest) {
        var accountMono = serverRequest.bodyToMono(AccountYanki.class);

        return accountMono.flatMap(t -> {
                    t.setMembershipDate(LocalDateTime.now());
                    return ServerResponse.status(HttpStatus.CREATED)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(accountRepository.save(t), AccountYanki.class);
                });
    }


    //public Mono<>

    public Mono<ServerResponse> findById(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        var accountItem = accountRepository.findById(id);


        return accountItem.flatMap(t -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(accountItem, AccountYanki.class)
                .switchIfEmpty(notFound)
        );
    }

    public Mono<ServerResponse> balance(ServerRequest serverRequest) {

        var accountItem = kafkaConsumer.getTransaction();
       return ServerResponse.ok()
               .contentType(MediaType.APPLICATION_JSON)
               .body(kafkaConsumer.getTransaction(), Transaction.class).switchIfEmpty(notFound);

    }

    public Mono<ServerResponse> findAll(ServerRequest serverRequest) {

        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(accountRepository.findAll().log("Func"), AccountYanki.class).switchIfEmpty(notFound);


    }

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        var accountItem = accountRepository.findById(id);
        var account = serverRequest.bodyToMono(AccountYanki.class);

        return accountItem.flatMap(
                t -> {
                    return account.flatMap(x -> {
                        t.setBalance(x.getBalance());
                        t.setMain(x.getMain());
                        return ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(accountRepository.save(t), AccountYanki.class);
                    });
                });

    }


}
