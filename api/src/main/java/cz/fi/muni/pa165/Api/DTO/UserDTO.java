package cz.fi.muni.pa165.api.dto;

import cz.fi.muni.pa165.enums.Role;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author rtrembecky
 */
public class UserDTO {
    private UUID id;

    private Role role;

    private String firstName;

    private String lastName;

    private String email;

    private Date created;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO that = (UserDTO) o;
        return  Objects.equals(id, that.id) &&
                Objects.equals(role, that.role) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(created, that.created) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, firstName, created, lastName, email);
    }
}
