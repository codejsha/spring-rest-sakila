package com.example.app.app.location.assembler;

import com.example.app.app.location.controller.LocationController;
import com.example.app.app.location.domain.dto.CityDto;
import com.example.app.common.constant.HalRelation;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CityRepresentationModelAssembler extends RepresentationModelAssemblerSupport<CityDto.City, CityDto.CityResponse> {
    public CityRepresentationModelAssembler() {
        super(LocationController.class, CityDto.CityResponse.class);
    }

    @Override
    @lombok.NonNull
    public CityDto.CityResponse toModel(@lombok.NonNull CityDto.City entity) {
        var model = instantiateModel(entity);
        model.setCity(entity);
        model.add(linkTo(methodOn(LocationController.class).getCity(String.valueOf(entity.getCityId()))).withSelfRel());
        model.add(linkTo(methodOn(LocationController.class).getAddressList()).withRel(HalRelation.Fields.addressList));
        return model;
    }
}