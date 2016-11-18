package cz.fi.muni.pa165.Model.Entity;

import cz.fi.muni.pa165.Model.DomainException;
import cz.fi.muni.pa165.Model.PersonName;
import cz.fi.muni.pa165.Model.Role;
import org.hibernate.annotations.ColumnTransformers;
import org.hibernate.validator.constraints.Email;
import org.jetbrains.annotations.Contract;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author jkuchar
 */
@SuppressWarnings("WeakerAccess")
@Entity
public class User {

    @Id
    @NotNull
    private UUID id;

    @NotNull
    @Enumerated
    private Role role;

    /**
     * Hibernate: embedded objects
     * @link http://www.simplecodestuffs.com/value-object-entity-object-in-hibernate-mapping/
     */
    @NotNull
    @Embedded
    private PersonName name;

    @Email
    @NotNull
    private String email;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    protected User() {}

    public User(PersonName personName, Role role, String email, Date created) {
        this.id = UUID.randomUUID();

        Assert.notNull(role, "Cannot create user with no role.");
        this.role = role;

        Assert.notNull(personName, "Cannot exist without name.");
        this.name = personName;

        Assert.notNull(email, "Cannot create user with no e-mail.");
        this.email = email;

        Assert.notNull(created, "Cannot create user without creation date.");
        this.created = created;
    }

    /**
     * Create user with current date created
     * @return created user
     */
    @Contract("_, _, _ -> !null")
    public static User create(PersonName personName, Role role, String email) {
        return new User(personName, role, email, new Date());
    }

    @org.jetbrains.annotations.Contract("!null, !null, !null, !null -> !null")
    public static User create(String firstName, String lastName, Role role, String email) {
        return User.create(new PersonName(firstName, lastName), role, email);
    }

    public void setRole(Role role){
        Assert.notNull(role, "Cannot change user's role to null.");
        this.role = role;
    }

    public void setName(PersonName personName){
        Assert.notNull(personName, "Cannot remove user's name.");
        this.name = personName;
    }

    public UUID getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public PersonName getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail() {
        return email;
    }
    
    public Date getCreated() {
        return created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
