package springwebfluxdemo.domain.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import springwebfluxdemo.domain.model.Store;

@Repository
public interface StoreRepository extends ReactiveCrudRepository<Store, Long> {
    Flux<Store> findByIdNotNull(Pageable pageable); // findAll not supported support only method query
}
