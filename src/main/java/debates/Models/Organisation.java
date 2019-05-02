package debates.Models;

/**
 * A unique entity which may be affiliated with any number of actors.
 */
public class Organisation {

    /**
     * The name of the organisation
     */
    private String name;

    /**
     * Constructor for an organisation
     * @param n The name of the organisation
     */
    public Organisation(String n) {
        this.name = n;
    }

}
