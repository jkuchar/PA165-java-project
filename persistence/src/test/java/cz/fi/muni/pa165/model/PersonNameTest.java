package cz.fi.muni.pa165.model;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

/**
 * This file is part of PA165 school project.
 * @author jkuchar
 */
public class PersonNameTest {


    public PersonName createPersonName() {
        return new PersonName("John", "Doe");
    }

    @Test
    public void testGetFirst() throws Exception {
        assertEquals(createPersonName().getFirst(), "John");
    }

    @Test
    public void testGetLast() throws Exception {
        assertEquals(createPersonName().getLast(), "Doe");
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(createPersonName(), createPersonName());
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(
            createPersonName().hashCode(),
            createPersonName().hashCode()
        );

    }

    @Test
    public void testOf_separateFirstNameAndLastName() throws Exception {
        assertEquals(
                createPersonName(),
                PersonName.of("John", "Doe")
        );

        assertNotEquals(
                createPersonName(),
                PersonName.of("John", "Smith")
        );

        assertNotEquals(
                createPersonName(),
                PersonName.of("Peter", "Doe")
        );
    }

    @Test
    public void testOf_compositeFistNameAndLastName() throws Exception {
        assertEquals(
                createPersonName(),
                PersonName.of("John Doe")
        );

        assertNotEquals(
                createPersonName(),
                PersonName.of("John Smith")
        );

        assertNotEquals(
                createPersonName(),
                PersonName.of("Peter Doe")
        );
    }

}