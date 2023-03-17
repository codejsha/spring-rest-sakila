package com.example.app.model.request;

import com.example.app.model.mapping.NullToEmptyStringSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("address")
    @JacksonXmlProperty(localName = "address")
    private String address;

    @JsonProperty("address2")
    @JacksonXmlProperty(localName = "address2")
    @JsonSerialize(nullsUsing = NullToEmptyStringSerializer.class)
    private String address2;

    @JsonProperty("district")
    @JacksonXmlProperty(localName = "district")
    private String district;

    @JsonProperty("cityId")
    @JacksonXmlProperty(localName = "cityId")
    private Integer cityId;

    @JsonProperty("postalCode")
    @JacksonXmlProperty(localName = "postalCode")
    private String postalCode;

    @JsonProperty("phone")
    @JacksonXmlProperty(localName = "phone")
    private String phone;

    // @JsonProperty("location")
    // @JacksonXmlProperty(localName = "location")
    // private Point location;
}