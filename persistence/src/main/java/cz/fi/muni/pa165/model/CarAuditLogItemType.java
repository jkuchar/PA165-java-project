package cz.fi.muni.pa165.model;

/**
 * This file is part of PA165 school project.
 */
public enum CarAuditLogItemType {

    APPLICATION_APPROVED {
        @Override
        public String getName() {
            return "Application approved record";
        }
    },
    APPLICATION_REJECTED {
        @Override
        public String getName() {
            return "Application rejected record";
        }
    },
    RENT_APPLICATION {
        @Override
        public String getName() {
            return "Rent application";
        }
    },
    RENT_RECORD {
        @Override
        public String getName() {
            return "Rent record";
        }
    },
    RETURN_RECORD {
        @Override
        public String getName() {
            return "Return record";
        }
    };

    public abstract String getName();

}
