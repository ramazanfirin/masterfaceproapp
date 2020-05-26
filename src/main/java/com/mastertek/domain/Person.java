package com.mastertek.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Person.
 */
@Entity
@Table(name = "person")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "uuid")
    private String uuid;

    @OneToMany(mappedBy = "person")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Image> images = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "person_staff_type",
               joinColumns = @JoinColumn(name="people_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="staff_types_id", referencedColumnName="id"))
    private Set<StaffType> staffTypes = new HashSet<>();

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

    public Person name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public Person surname(String surname) {
        this.surname = surname;
        return this;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUuid() {
        return uuid;
    }

    public Person uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Set<Image> getImages() {
        return images;
    }

    public Person images(Set<Image> images) {
        this.images = images;
        return this;
    }

    public Person addImages(Image image) {
        this.images.add(image);
        image.setPerson(this);
        return this;
    }

    public Person removeImages(Image image) {
        this.images.remove(image);
        image.setPerson(null);
        return this;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<StaffType> getStaffTypes() {
        return staffTypes;
    }

    public Person staffTypes(Set<StaffType> staffTypes) {
        this.staffTypes = staffTypes;
        return this;
    }

    public Person addStaffType(StaffType staffType) {
        this.staffTypes.add(staffType);
        return this;
    }

    public Person removeStaffType(StaffType staffType) {
        this.staffTypes.remove(staffType);
        return this;
    }

    public void setStaffTypes(Set<StaffType> staffTypes) {
        this.staffTypes = staffTypes;
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
        Person person = (Person) o;
        if (person.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), person.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Person{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", uuid='" + getUuid() + "'" +
            "}";
    }
}
