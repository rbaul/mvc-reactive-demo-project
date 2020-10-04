package springmvcdemo.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import springmvcdemo.domain.repositories.ProductRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl {
    private ProductRepository productRepository;
}
