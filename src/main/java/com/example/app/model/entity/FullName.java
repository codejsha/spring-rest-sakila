package com.example.app.model.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.*;

@Embeddable
@Getter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FullName {
    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    @NonNull
    @Size(min = 1, max = 45)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false, length = 45)
    @NonNull
    @Size(min = 1, max = 45)
    private String lastName;
}