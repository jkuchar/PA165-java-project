/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.enums;


/**
 * @author jakubsarmir
 * @author jkuchar
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
