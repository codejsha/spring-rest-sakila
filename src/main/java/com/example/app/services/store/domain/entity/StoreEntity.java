package com.example.app.services.store.domain.entity;

import com.example.app.common.domain.dto.Updatable;
import com.example.app.services.location.domain.entity.AddressEntity;
import com.example.app.services.staff.domain.entity.StaffEntity;
import com.google.common.base.Objects;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "store")
@Table(name = "store", schema = "sakila", indexes = {
        @Index(name = "idx_unique_manager", columnList = "manager_staff_id", unique = true),
        @Index(name = "idx_fk_address_id", columnList = "address_id")
})
@Getter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StoreEntity implements Serializable, Updatable<StoreEntity> {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "store_id", columnDefinition = "TINYINT UNSIGNED", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storeId;

    @Basic
    @Column(name = "manager_staff_id", columnDefinition = "TINYINT UNSIGNED", nullable = false,
            insertable = false, updatable = false)
    @NotNull
    private Integer managerStaffId;

    @Basic
    @Column(name = "address_id", columnDefinition = "SMALLINT UNSIGNED", nullable = false,
            insertable = false, updatable = false)
    @NotNull
    private Integer addressId;

    @Basic
    @Column(name = "last_update", columnDefinition = "TIMESTAMP", nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    @UpdateTimestamp
    @NotNull
    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "manager_staff_id", referencedColumnName = "staff_id", nullable = false)
    @NotNull
    @ToString.Exclude
    private StaffEntity staffByManagerStaffId;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false)
    @NotNull
    @ToString.Exclude
    private AddressEntity addressByAddressId;

    @Override
    public void update(StoreEntity entity) {
        this.managerStaffId = entity.managerStaffId;
        this.addressId = entity.addressId;
        this.lastUpdate = entity.lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final StoreEntity that = (StoreEntity) o;
        return Objects.equal(storeId, that.storeId)
                && Objects.equal(managerStaffId, that.managerStaffId)
                && Objects.equal(addressId, that.addressId)
                && Objects.equal(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(storeId, managerStaffId, addressId, lastUpdate);
    }
}
