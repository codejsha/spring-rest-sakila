package com.example.app.services.catalog.domain.entity;

import com.example.app.common.domain.dto.Updatable;
import com.google.common.base.Objects;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "film_actor")
@Table(name = "film_actor", schema = "sakila", indexes = {
        @Index(name = "idx_fk_film_id", columnList = "film_id")
})
@IdClass(FilmActorEntityPK.class)
@Getter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FilmActorEntity implements Serializable, Updatable<FilmActorEntity> {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "actor_id", columnDefinition = "SMALLINT UNSIGNED", nullable = false,
            insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actorId;

    @Id
    @Column(name = "film_id", columnDefinition = "SMALLINT UNSIGNED", nullable = false,
            insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer filmId;

    @Basic
    @Column(name = "last_update", columnDefinition = "TIMESTAMP", nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    @UpdateTimestamp
    @NotNull
    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "actor_id", referencedColumnName = "actor_id", nullable = false)
    @NotNull
    @ToString.Exclude
    private ActorEntity actorByActorId;

    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", nullable = false)
    @NotNull
    @ToString.Exclude
    private FilmEntity filmByFilmId;

    @Override
    public void update(FilmActorEntity entity) {
        this.actorId = entity.actorId;
        this.filmId = entity.filmId;
        this.lastUpdate = entity.lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final FilmActorEntity that = (FilmActorEntity) o;
        return Objects.equal(actorId, that.actorId)
                && Objects.equal(filmId, that.filmId)
                && Objects.equal(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(actorId, filmId, lastUpdate);
    }
}
