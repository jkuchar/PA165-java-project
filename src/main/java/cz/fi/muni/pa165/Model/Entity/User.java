package cz.fi.muni.pa165.Model.Entity;

import cz.fi.muni.pa165.Model.DomainException;
import cz.fi.muni.pa165.Model.Role;
import org.hibernate.validator.constraints.Email;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
class User {

    /* DO NOT REMOVE! hack explanation @link http://stackoverflow.com/questions/2935826/why-does-hibernate-require-no-argument-constructor#comment9688725_2971717 */
    protected User() {
    }

    User(String firstName, String lastName, Role role, String email, Date created) {
        this.id = UUID.randomUUID();

        Assert.notNull(role, "Cannot create user with no role.");
        this.role = role;

        Assert.notNull(firstName, "Cannot create user with no first name.");
        this.firstName = firstName;

        Assert.notNull(lastName, "Cannot create user with no last name.");
        this.lastName = lastName;

        Assert.notNull(email, "Cannot create user with no e-mail.");
        this.email = email;

        Assert.notNull(created, "Cannot create user without creation date.");
        this.created = created;
    }

    public static User create(String firstName, String lastName, Role role, String email) {
        return new User(firstName, lastName, role, email, new Date());
    }

    @Id
    @NotNull
    private UUID id;

    @NotNull
    private Role role;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Email
    @NotNull
    private String email;

    @NotNull
    private Date created;

    public void changeRole(Role role) throws DomainException {
        Assert.notNull(role, "Cannot change user's role to null.");
        this.role = role;
    }

    public void changeName(String firstName, String lastName) throws DomainException {
        Assert.notNull(firstName, "First name is required when changing user's name.");
        Assert.notNull(lastName,  "Last name is required when changing user's name.");

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }
    public Role getRole() {
        return role;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() { return email; }
    public Date getCreated() { return created; }
}
