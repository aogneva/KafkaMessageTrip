package org.ogneva.consumer.model;

import org.ogneva.PersonDTO;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="person_name")
    @NotNull
    private String name;

    @Column(name="year")
    @NotNull
    private Integer year;

    public PersonEntity() {}

    public PersonEntity(String name, Integer year) {
        this.name = name;
        this.year = year;
    }

    public PersonEntity(Long id, String name, Integer year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public PersonDTO toDto() {
        if (this == null) return null;
        return new PersonDTO(this.getId(), this.getName(), this.getYear());
    }

    public static PersonEntity toEntity(PersonDTO dto) {
        if (dto == null) return null;
        return new PersonEntity(dto.getId(), dto.getName(), dto.getYear());
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PersonEntity other = (PersonEntity) obj;
        if (name == null) {
            if (other.getName() != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (year == null) {
            if (other.year != null)
                return false;
        } else if (!year.equals(other.year))
            return false;
        return true;
    }

}
