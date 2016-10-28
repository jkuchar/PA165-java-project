package cz.fi.muni.pa165.Model;

public class DomainException extends java.lang.Exception {

    private DomainException(String message) {
        super(message);
    }

    public static DomainException someError() {
        // change me...
        return new DomainException(
                String.format("Some error occurred.")
        );
    }

    // add more named constructors here...
}
