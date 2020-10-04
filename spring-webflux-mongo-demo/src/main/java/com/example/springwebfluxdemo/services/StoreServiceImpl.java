package com.example.springwebfluxdemo.services;

import com.example.springwebfluxdemo.domain.model.Store;
import com.example.springwebfluxdemo.domain.repositories.StoreRepository;
import com.example.springwebfluxdemo.services.exceptions.NotFoundException;
import com.example.springwebfluxdemo.web.dtos.StoreDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreServiceImpl {
    private final StoreRepository storeRepository;

    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public Mono<StoreDto> get(long id) {
        return storeRepository.findById(id)
                .map(store -> modelMapper.map(store, StoreDto.class));
    }

    @Transactional(readOnly = true)
    public Flux<StoreDto> getAll() {
        return storeRepository.findAll()
                .map(store -> modelMapper.map(store, StoreDto.class));
    }

    @Transactional(readOnly = true)
    public Flux<StoreDto> getAll(Pageable pageable) {
        return storeRepository.findByIdNotNull(pageable)
                .map(store -> modelMapper.map(store, StoreDto.class));
    }

    @Transactional
    public Mono<StoreDto> create(StoreDto storeDto) {
        return storeRepository.save(modelMapper.map(storeDto, Store.class))
                .map(store -> modelMapper.map(store, StoreDto.class));
    }

    @Transactional
    public Mono<StoreDto> update(long id, StoreDto storeDto) {
//        return storeRepository.findById(id)
//                .map(store -> {
//                    modelMapper.map(storeDto, store);
//                    return store;
//                }).flatMap(storeRepository::save)
//                .map(store -> modelMapper.map(store, StoreDto.class));
        return storeRepository.findById(id)
                .doOnSuccess(store -> modelMapper.map(storeDto, store))
                .map(store -> modelMapper.map(store, StoreDto.class));
    }

    @Transactional
    public Mono<Void> delete(long id) {
        return storeRepository.existsById(id)
                .flatMap(isExists -> {
                    if (isExists) {
                        throw NotFoundException.createNotFoundException(id);
                    } else {
                        return storeRepository.deleteById(id);
                    }
                });

//        return storeRepository.findById(id)
//                .flatMap(storeRepository::delete);

    }

    private Mono<Store> getStoreById(long id) {
        return storeRepository.findById(id);
    }


}
