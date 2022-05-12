package com.nttdata.repository;

import com.nttdata.document.AccountYanki;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountYankiRepository extends ReactiveMongoRepository<AccountYanki,String> {
}
