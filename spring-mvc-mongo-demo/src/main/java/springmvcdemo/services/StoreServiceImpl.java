package springmvcdemo.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springmvcdemo.domain.model.Store;
import springmvcdemo.domain.repositories.StoreRepository;
import springmvcdemo.services.exceptions.NotFoundException;
import springmvcdemo.web.dtos.StoreDto;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreServiceImpl {
    private final StoreRepository storeRepository;

    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public StoreDto get(long id) {
        Store persistedStore = getStoreById(id);
        return modelMapper.map(persistedStore, StoreDto.class);
    }

    @Transactional(readOnly = true)
    public List<StoreDto> getAll() {
        return storeRepository.findAll().stream()
                .map(store -> modelMapper.map(store, StoreDto.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<StoreDto> getAll(Pageable pageable) {
        return storeRepository.findAll(pageable)
                .map(store -> modelMapper.map(store, StoreDto.class));
    }

    @Transactional
    public StoreDto create(StoreDto storeDto) {
        Store store = modelMapper.map(storeDto, Store.class);
        Store persistedStore = storeRepository.save(store);
        return modelMapper.map(persistedStore, StoreDto.class);
    }

    @Transactional
    public StoreDto update(long id, StoreDto storeDto) {
        Store persistedStore = getStoreById(id);
        modelMapper.map(storeDto, persistedStore); // Update
        return modelMapper.map(persistedStore, StoreDto.class);
    }

    @Transactional
    public void delete(long id) {
        boolean isExists = storeRepository.existsById(id);
        if (!isExists) {
            throw NotFoundException.createNotFoundException(id);
        }
        storeRepository.deleteById(id);
    }

    private Store getStoreById(long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> NotFoundException.createNotFoundException(id));
    }


}
