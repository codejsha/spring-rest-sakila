package com.example.app.services.catalog.domain.dto;

import com.example.app.services.catalog.domain.converter.CategoryConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import jakarta.persistence.Convert;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

public class CategoryDto {
    @Getter
    @Setter
    @ToString
    @FieldNameConstants
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Category {
        @JsonProperty(Fields.categoryId)
        @Convert(converter = CategoryConverter.class)
        private com.example.app.common.constant.Category categoryId;

        @JsonProperty(Fields.name)
        @Size(min = 1, max = 25)
        private String name;

        @JsonProperty(Fields.lastUpdate)
        private LocalDateTime lastUpdate;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Category that = (Category) o;
            return categoryId == that.categoryId
                    && Objects.equal(name, that.name)
                    && Objects.equal(lastUpdate, that.lastUpdate);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(categoryId, name, lastUpdate);
        }
    }
}
