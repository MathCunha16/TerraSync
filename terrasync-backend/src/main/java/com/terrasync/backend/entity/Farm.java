package com.terrasync.backend.entity;

import com.terrasync.backend.entity.base.BaseEntity;
import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "farms", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "name"}))
public class Farm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(name = "size_in_hectares")
    private BigDecimal sizeInHectares;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = true)
    private Point geolocation;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true; // Settando por padrão true

    public Farm() {
        // Construtor vazio para JPA
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Point getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Point geolocation) {
        this.geolocation = geolocation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getSizeInHectares() {
        return sizeInHectares;
    }

    public void setSizeInHectares(BigDecimal sizeInHectares) {
        this.sizeInHectares = sizeInHectares;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Farm farm = (Farm) o;
        return Objects.equals(id, farm.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
