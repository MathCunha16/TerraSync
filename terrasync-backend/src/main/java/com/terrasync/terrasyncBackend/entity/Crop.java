package com.terrasync.terrasyncBackend.entity;

import com.terrasync.terrasyncBackend.entity.base.BaseEntity;
import com.terrasync.terrasyncBackend.entity.enums.CropStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "crops", uniqueConstraints = @UniqueConstraint(columnNames = {"farm_id", "name"}))
public class Crop extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_types_id", nullable = false)
    private CropType cropType;

    @Column(nullable = false)
    private String name;

    @Column(name = "planting_date")
    private LocalDate plantingDate;

    @Column(name = "harvest_date")
    private LocalDate harvestDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CropStatus status = CropStatus.PLANTED;


    public Crop() {
        // Construtor vazio para JPA
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public CropType getCropType() {
        return cropType;
    }

    public void setCropType(CropType cropType) {
        this.cropType = cropType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(LocalDate plantingDate) {
        this.plantingDate = plantingDate;
    }

    public LocalDate getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(LocalDate harvestDate) {
        this.harvestDate = harvestDate;
    }

    public CropStatus getStatus() {
        return status;
    }

    public void setStatus(CropStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crop crop = (Crop) o;
        return Objects.equals(id, crop.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
