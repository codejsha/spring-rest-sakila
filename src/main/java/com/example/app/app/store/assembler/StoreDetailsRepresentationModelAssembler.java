package com.example.app.app.store.assembler;

import com.example.app.app.store.controller.StoreController;
import com.example.app.app.store.domain.dto.StoreDetailsDto;
import com.example.app.common.constant.HalRelation;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StoreDetailsRepresentationModelAssembler extends RepresentationModelAssemblerSupport<StoreDetailsDto.StoreDetails, StoreDetailsDto.StoreDetailsResponse> {
    public StoreDetailsRepresentationModelAssembler() {
        super(StoreController.class, StoreDetailsDto.StoreDetailsResponse.class);
    }

    @Override
    @lombok.NonNull
    public StoreDetailsDto.StoreDetailsResponse toModel(@lombok.NonNull StoreDetailsDto.StoreDetails entity) {
        var model = instantiateModel(entity);
        model.setStoreDetails(entity);
        model.add(linkTo(methodOn(StoreController.class).getStoreDetails(String.valueOf(entity.getId()))).withSelfRel());
        model.add(linkTo(methodOn(StoreController.class).getStore(String.valueOf(entity.getId()))).withRel(HalRelation.Fields.store));
        model.add(linkTo(methodOn(StoreController.class).getStoreList()).withRel(HalRelation.Fields.storeList));
        return model;
    }
}