package com.terrasync.terrasync_backend.entity;

import com.terrasync.terrasync_backend.entity.base.BaseEntity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "crop_types")
public class CropType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CropType cropType = (CropType) o;
        return Objects.equals(id, cropType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
