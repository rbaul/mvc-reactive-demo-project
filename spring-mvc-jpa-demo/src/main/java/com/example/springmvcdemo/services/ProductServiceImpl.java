package com.example.springmvcdemo.services;

import com.example.springmvcdemo.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl {
    private ProductRepository productRepository;
}
