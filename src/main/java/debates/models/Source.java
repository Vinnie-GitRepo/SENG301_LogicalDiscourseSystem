package debates.models;

/**
 * A named collection of any number of Discourse objects.
 */
public class Source {

    /**
     * The name of the source.
     */
    private String name;


    /**
     * The calculated level of validity associated with a source.
     * This value will be used when calculating the level of trust associated with an actor.
     */
    private double confidenceLevel;


    /**
     * Constructor for a source
     * @param n The name of the source.
     * @param c The calculated level of validity associated with a source.
     */
    public Source(String n, double c) {
        this.name = n;
        this.confidenceLevel = c;
    }
}
