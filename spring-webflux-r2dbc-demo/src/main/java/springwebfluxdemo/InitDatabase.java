package springwebfluxdemo;

import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import reactor.core.publisher.Flux;
import springwebfluxdemo.domain.model.Store;
import springwebfluxdemo.domain.repositories.StoreRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class InitDatabase {

    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));

        return initializer;
    }

    @Bean
    public CommandLineRunner populateDatabase(StoreRepository storeRepository) {
        return (args) -> {
            List<Store> stores = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Store store1 = Store.builder()
//                    .id((long) i)
                        .name("Store" + i)
                        .build();

//            Product product1 = Product.builder()
//                    .id((long) i + 100)
//                    .name(store1.getName() + " - Product 1")
//                    .description("Product Description")
//                    .price(12d)
//                    .quantity((short) 10)
////                    .store(store1)
//                    .build();
//            Product product2 = Product.builder()
//                    .id((long) i + 200)
//                    .name(store1.getName() + " - Product 2")
//                    .description("Product Description")
//                    .price(120d)
//                    .quantity((short) 100)
////                    .store(store1)
//                    .build();
//
//            store1.setProducts(new ArrayList<>(Arrays.asList(product1, product2)));
                stores.add(store1);
            }

            Flux<Store> storeFlux = storeRepository.saveAll(stores);
            storeFlux
                    .thenMany(storeRepository.findAll())
                    .subscribe(System.out::println);
        };
    }

//    @Override
//    protected List<Object> getCustomConverters() {
//
//        List<Converter<?, ?>> converterList = new ArrayList<Converter<?, ?>>();
//        converterList.add(new org.springframework.data.r2dbc.test.PersonReadConverter());
//        converterList.add(new org.springframework.data.r2dbc.test.PersonWriteConverter());
//        return converterList;
//    }
}
