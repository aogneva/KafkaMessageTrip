package org.ogneva;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    Long id;

    String name;

    int year;

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
        PersonDTO other = (PersonDTO) obj;
        if (name == null) {
            if (other.getName() != null)
                return false;
        } else if (!name.equals(other.getName()))
            return false;
        if (year != other.getYear())
            return false;
        return true;
    }

}
