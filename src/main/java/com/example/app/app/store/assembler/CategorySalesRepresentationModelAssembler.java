package com.example.app.app.store.assembler;

import com.example.app.app.store.controller.ReportController;
import com.example.app.app.store.domain.dto.CategorySalesDto;
import com.example.app.common.constant.HalRelation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CategorySalesRepresentationModelAssembler extends RepresentationModelAssemblerSupport<CategorySalesDto.CategorySales, CategorySalesDto.CategorySalesResponse> {
    public CategorySalesRepresentationModelAssembler() {
        super(ReportController.class, CategorySalesDto.CategorySalesResponse.class);
    }

    @Override
    @lombok.NonNull
    public CategorySalesDto.CategorySalesResponse toModel(@lombok.NonNull CategorySalesDto.CategorySales entity) {
        var model = instantiateModel(entity);
        model.setCategorySales(entity);
        return model;
    }

    @Override
    @lombok.NonNull
    public CollectionModel<CategorySalesDto.CategorySalesResponse> toCollectionModel(@lombok.NonNull Iterable<? extends CategorySalesDto.CategorySales> entities) {
        var collectionModel = super.toCollectionModel(entities);
        collectionModel.add(linkTo(methodOn(ReportController.class).reportSalesByCategory()).withSelfRel());
        collectionModel.add(linkTo(methodOn(ReportController.class).reportSalesByStore()).withRel(HalRelation.Fields.storeSales));
        return collectionModel;
    }
}