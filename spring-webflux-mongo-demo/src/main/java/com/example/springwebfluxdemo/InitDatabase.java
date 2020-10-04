package com.example.springwebfluxdemo;

import com.example.springwebfluxdemo.domain.model.Product;
import com.example.springwebfluxdemo.domain.model.Store;
import com.example.springwebfluxdemo.domain.repositories.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitDatabase implements ApplicationRunner {

    private final StoreRepository storeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Store> stores = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Store store1 = Store.builder()
                    .id((long)i)
                    .name("Store" + i)
                    .build();

            Product product1 = Product.builder()
                    .id((long) i + 100)
                    .name(store1.getName() + " - Product 1")
                    .description("Product Description")
                    .price(12d)
                    .quantity((short) 10)
//                    .store(store1)
                    .build();
            Product product2 = Product.builder()
                    .id((long) i + 200)
                    .name(store1.getName() + " - Product 2")
                    .description("Product Description")
                    .price(120d)
                    .quantity((short) 100)
//                    .store(store1)
                    .build();

            store1.setProducts(new ArrayList<>(Arrays.asList(product1, product2)));
            stores.add(store1);
        }

        Flux<Store> storeFlux = storeRepository.saveAll(stores);
        storeFlux
                .thenMany(storeRepository.findAll())
                .subscribe(System.out::println);
    }
}
