
package cz.cvut.kbss.ear.zpevnik.model;

/**
 *
 * @author Adam Škarda
 */
public enum UserRoles {
    ADMIN(Values.ADMIN),
    REGULAR(Values.REGULAR),
    VERIFIED(Values.VERIFIED);
    
    private final String name;
    
    public static class Values{
        public static final String ADMIN = "ADMIN";
        public static final String REGULAR = "REGULAR";
        public static final String VERIFIED = "VERIFIED";
    }
    private UserRoles(String name) {    
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
