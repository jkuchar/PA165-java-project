package cz.fi.muni.pa165.Model;

/**
 * Created by SARMIR on 27. 10. 2016.
 */
public enum CarState {


    OK {
        @Override
        public boolean allowTransition(CarState state) {
            return state == CarState.SERVICING || state == CarState.DISCARDED;
        }
    },

    SERVICING {
        @Override
        public boolean allowTransition(CarState state) {
            return state == CarState.OK || state == CarState.DISCARDED;
        }
    },

    DISCARDED {
        @Override
        public boolean allowTransition(CarState state) {
            return false;
        }
    };


    public abstract boolean allowTransition(CarState state);
}
