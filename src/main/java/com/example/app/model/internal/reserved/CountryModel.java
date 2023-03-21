package com.example.app.model.internal.reserved;

import com.example.app.model.enumeration.Country;
import com.example.app.model.mapping.converter.CountryConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import jakarta.persistence.Convert;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryModel {
    @JsonProperty("countryId")
    private Country countryId;

    @JsonProperty("country")
    @Convert(converter = CountryConverter.class)
    private Country country;

    @JsonProperty("lastUpdate")
    private LocalDateTime lastUpdate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryModel that = (CountryModel) o;
        return countryId == that.countryId
                && country == that.country
                && Objects.equal(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(countryId, country, lastUpdate);
    }
}