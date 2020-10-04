package com.example.springwebfluxdemo.domain.repositories;

import com.example.springwebfluxdemo.domain.model.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StoreRepository extends ReactiveMongoRepository<Store, Long> {
    Flux<Store> findByIdNotNull(Pageable pageable); // findAll not supported support only method query
}
