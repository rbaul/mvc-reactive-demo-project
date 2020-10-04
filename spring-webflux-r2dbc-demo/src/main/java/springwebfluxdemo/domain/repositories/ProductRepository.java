package springwebfluxdemo.domain.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import springwebfluxdemo.domain.model.Product;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
}
