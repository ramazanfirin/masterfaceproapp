package com.mastertek.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.mastertek.domain.enumeration.LocationType;

/**
 * A Location.
 */
@Entity
@Table(name = "location")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_type")
    private LocationType type;

    @ManyToOne
    private Floor floor;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "location_white_list",
               joinColumns = @JoinColumn(name="locations_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="white_lists_id", referencedColumnName="id"))
    private Set<StaffType> whiteLists = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Location name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationType getType() {
        return type;
    }

    public Location type(LocationType type) {
        this.type = type;
        return this;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public Floor getFloor() {
        return floor;
    }

    public Location floor(Floor floor) {
        this.floor = floor;
        return this;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Set<StaffType> getWhiteLists() {
        return whiteLists;
    }

    public Location whiteLists(Set<StaffType> staffTypes) {
        this.whiteLists = staffTypes;
        return this;
    }

    public Location addWhiteList(StaffType staffType) {
        this.whiteLists.add(staffType);
        return this;
    }

    public Location removeWhiteList(StaffType staffType) {
        this.whiteLists.remove(staffType);
        return this;
    }

    public void setWhiteLists(Set<StaffType> staffTypes) {
        this.whiteLists = staffTypes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        if (location.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), location.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Location{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
