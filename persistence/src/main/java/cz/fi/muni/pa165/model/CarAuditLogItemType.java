package cz.fi.muni.pa165.model;

import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.CarAuditLogItem;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of PA165 school project.
 */
public enum CarAuditLogItemType {

    RENT_APPLICATION {
        @Override
        public String getName() {
            return "Rent application";
        }


        @Override
        public CarAuditLogItemType[] getPossibleSuccessors() {
            return new CarAuditLogItemType[]{
                    CarAuditLogItemType.APPLICATION_APPROVED,
                    CarAuditLogItemType.APPLICATION_REJECTED
            };
        }

        @Override
        public boolean isInitialState() {
            return true;
        }

        @Override
        public boolean isEndState() {
            return false;
        }
    },

    APPLICATION_REJECTED {
        @Override
        public String getName() {
            return "Application rejected record";
        }


        @Override
        public CarAuditLogItemType[] getPossibleSuccessors() {
            return new CarAuditLogItemType[] {
                    CarAuditLogItemType.RENT_APPLICATION
            };
        }

        @Override
        public boolean isInitialState() {
            return false;
        }

        @Override
        public boolean isEndState() {
            return true;
        }
    },

    APPLICATION_APPROVED {
        @Override
        public String getName() {
            return "Application approved record";
        }

        @Override
        public CarAuditLogItemType[] getPossibleSuccessors() {
            return new CarAuditLogItemType[] {
                CarAuditLogItemType.RENT_RECORD
            };
        }

        @Override
        public boolean isInitialState() {
            return false;
        }

        @Override
        public boolean isEndState() {
            return false;
        }
    },

    RENT_RECORD {
        @Override
        public String getName() {
            return "Rent record";
        }


        @Override
        public CarAuditLogItemType[] getPossibleSuccessors() {
            return new CarAuditLogItemType[] {
                    CarAuditLogItemType.RETURN_RECORD
            };
        }

        @Override
        public boolean isInitialState() {
            return false;
        }

        @Override
        public boolean isEndState() {
            return false;
        }
    },

    RETURN_RECORD {
        @Override
        public String getName() {
            return "Return record";
        }

        @Override
        public CarAuditLogItemType[] getPossibleSuccessors() {
            return new CarAuditLogItemType[] {
                    CarAuditLogItemType.RENT_APPLICATION
            };
        }

        @Override
        public boolean isInitialState() {
            return false;
        }

        @Override
        public boolean isEndState() {
            return true;
        }
    };

    public abstract String getName();
    public abstract CarAuditLogItemType[] getPossibleSuccessors();

    /** Initial state can be the first record for given car */
    public abstract boolean isInitialState();

    /** Is end state? After this must be again initial state */
    public abstract boolean isEndState();

    /**
     * Can given record type go after current one?
     */
    public boolean canBeNext(CarAuditLogItemType nextType) {
        return ArrayUtils.indexOf(getPossibleSuccessors(), nextType) != -1; // if found, then true
    };

    public static CarAuditLogItemType getInitialState() {
        return CarAuditLogItemType.RENT_APPLICATION;
    }

}
