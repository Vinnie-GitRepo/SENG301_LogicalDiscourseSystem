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
     * Constructor for a source
     * @param n The name of the source.
     */
    public Source(String n) {
        this.name = n;
    }
}
