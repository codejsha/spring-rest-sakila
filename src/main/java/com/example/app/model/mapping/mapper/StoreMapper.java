package com.example.app.model.mapping.mapper;

import com.example.app.model.entity.StoreEntity;
import com.example.app.model.internal.StoreModel;
import com.example.app.model.request.StoreRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface StoreMapper extends GenericMapper<StoreEntity, StoreModel> {
    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    @Mapping(target = "storeId", ignore = true)
    @Mapping(target = "lastUpdate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "customersByStoreId", ignore = true)
    @Mapping(target = "inventoriesByStoreId", ignore = true)
    @Mapping(target = "staffByStoreId", ignore = true)
    @Mapping(target = "staffByManagerStaffId", ignore = true)
    @Mapping(target = "addressByAddressId", ignore = true)
    StoreEntity mapToEntity(StoreRequestModel dto);
}
