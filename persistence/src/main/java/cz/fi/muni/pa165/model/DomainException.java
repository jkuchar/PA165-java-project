package cz.fi.muni.pa165.model;

/**
 * @author jkuchar
 */
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

    public static DomainException carStateTransitionNotAllowed(CarState state0, CarState state1) {
        return new DomainException(
            String.format("Car cannot translate from state %s to %s.", state0, state1)
        );
    }

    // add more named constructors here...
}
