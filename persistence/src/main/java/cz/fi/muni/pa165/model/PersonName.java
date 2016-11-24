package cz.fi.muni.pa165.model;

import org.jetbrains.annotations.Contract;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Person name Value Object
 * @link http://martinfowler.com/bliki/ValueObject.html
 * @todo add of() static factory?
 * @author jkuchar
 */
@SuppressWarnings("JpaAttributeMemberSignatureInspection")
@Embeddable
public class PersonName {

    @NotNull
    @Column(name = "name_first")
    private String first;

    @NotNull
    @Column(name = "name_last")
    private String last;

    protected PersonName() {
    }

    public PersonName(String firstName, String lastName) {
        Assert.notNull(firstName, "Person without first name cannot exist.");
        Assert.notNull(lastName, "Person without last name cannot exist.");

        this.first = firstName;
        this.last = lastName;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonName)) return false;
        PersonName that = (PersonName) o;
        return Objects.equals(first, that.first) &&
                Objects.equals(last, that.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last);
    }

    @Contract("_, _ -> !null")
    public static PersonName of(String first, String last) {
        return new PersonName(first, last);
    }

    public static PersonName of(String name) {
        String[] parts = name.split("\\s+");

        Assert.state(parts.length == 2, "Can only construct names from simple strings like a \"John Doe\".");

        return PersonName.of(parts[0], parts[1]);
    }
}
