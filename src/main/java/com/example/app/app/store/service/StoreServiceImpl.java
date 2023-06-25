package com.example.app.app.store.service;

import com.example.app.app.staff.domain.dto.StaffDto;
import com.example.app.app.store.domain.dto.InventoryDto;
import com.example.app.app.store.domain.dto.StoreDetailsDto;
import com.example.app.app.store.domain.dto.StoreDto;
import com.example.app.app.store.domain.mapper.InventoryMapper;
import com.example.app.app.store.domain.mapper.StoreMapper;
import com.example.app.app.store.repository.InventoryRepository;
import com.example.app.app.store.repository.StoreRepository;
import com.example.app.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final InventoryRepository inventoryRepository;
    private final StoreMapper storeMapper;
    private final InventoryMapper inventoryMapper;

    @Override
    @Transactional(readOnly = true)
    public List<StoreDto.Store> getStoreList(Pageable pageable) {
        var list = storeRepository.findAll(pageable);
        return storeMapper.mapToDtoList(list);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StoreDto.Store> getStore(Integer storeId) {
        var entity = storeRepository.findById(storeId).orElseThrow(() ->
                new ResourceNotFoundException("Store not found with id '" + storeId + "'"));
        return Optional.of(storeMapper.mapToDto(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StoreDetailsDto.StoreDetails> getStoreDetailsList() {
        return storeRepository.findAllStoreDetailsList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StoreDetailsDto.StoreDetails> getStoreDetails(Integer storeId) {
        var model = storeRepository.findStoreDetailsById(storeId);
        if (model.isEmpty()) {
            throw new ResourceNotFoundException("Store not found with id '" + storeId + "'");
        }
        return model;
    }

    @Override
    @Transactional(readOnly = true)
    public List<StaffDto.Staff> getStoreStaffList(Integer storeId) {
        return storeRepository.findAllStoreStaffList(storeId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StaffDto.Staff> getStoreStaff(Integer storeId, Integer staffId) {
        var model = storeRepository.findStoreStaffById(storeId, staffId);
        if (model.isEmpty()) {
            throw new ResourceNotFoundException("Staff not found with id '" + staffId + "'");
        }
        return model;
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryDto.Inventory> checkInventoryStock(Integer storeId, Integer filmId) {
        var list = inventoryRepository.findAllByStoreIdAndFilmId(storeId, filmId);
        return inventoryMapper.mapToDtoList(list);
    }

    @Override
    @Transactional
    public StoreDto.Store addStore(StoreDto.StoreRequest model) {
        var savedEntity = storeRepository.save(storeMapper.mapToEntity(model));
        return storeMapper.mapToDto(savedEntity);
    }

    @Override
    @Transactional
    public StaffDto.Staff addStoreStaff(Integer storeId, Integer staffId) {
        return null;
    }

    @Override
    @Transactional
    public StoreDto.Store updateStore(Integer storeId, StoreDto.StoreRequest model) {
        var entity = storeRepository.findById(storeId).orElseThrow(() ->
                new ResourceNotFoundException("Store not found with id '" + storeId + "'"));
        entity.update(storeMapper.mapToEntity(model));
        return storeMapper.mapToDto(entity);
    }

    @Override
    @Transactional
    public StaffDto.Staff updateStoreStaff(Integer storeId, Integer staffId) {
        return null;
    }

    @Override
    @Transactional
    public void deleteStore(Integer storeId) {
        storeRepository.deleteById(storeId);
    }

    @Override
    @Transactional
    public void removeStoreStaff(Integer storeId, Integer staffId) {
    }
}
