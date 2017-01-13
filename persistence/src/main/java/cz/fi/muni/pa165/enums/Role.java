package cz.fi.muni.pa165.enums;

/**
 * @author jkuchar
 */
public enum Role {
    USER, MANAGER, ADMIN;

    public String getAuthority() {
        return "ROLE_" + name();
    }
}
