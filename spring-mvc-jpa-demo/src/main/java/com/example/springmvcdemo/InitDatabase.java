package com.example.springmvcdemo;

import com.example.springmvcdemo.domain.model.Product;
import com.example.springmvcdemo.domain.model.Store;
import com.example.springmvcdemo.domain.repositories.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitDatabase implements ApplicationRunner {

    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        List<Store> stores = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Store store1 = Store.builder()
                    .name("Store" + i)
                    .build();

            Product product1 = Product.builder()
                    .name(store1.getName() + " - Product 1")
                    .description("Product Description")
                    .price(12d)
                    .quantity((short) 10)
                    .store(store1)
                    .build();
            Product product2 = Product.builder()
                    .name(store1.getName() + " - Product 2")
                    .description("Product Description")
                    .price(120d)
                    .quantity((short) 100)
                    .store(store1)
                    .build();

            store1.setProducts(new ArrayList<>(Arrays.asList(product1, product2)));
            stores.add(store1);
        }

        storeRepository.saveAll(stores);
        storeRepository.findAll().forEach(System.out::println);
    }
}
