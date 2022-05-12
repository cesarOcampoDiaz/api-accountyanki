package com.nttdata.repository;

import com.nttdata.document.AccountYanki;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AccountYankiRepository extends ReactiveMongoRepository<AccountYanki,String> {

    Mono<AccountYanki> findByClientId(Integer clientId);
}
