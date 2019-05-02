package debates.models;


import java.util.ArrayList;

/**
 * A named collection of Argument objects taken from a Source.
 */
public class Discourse {

    /**
     * Name of the discourse
     */
    private String name;

    /**
     * The source from which the arguments are taken from
     */
    private Source source;

    /**
     * A list of Argument objects
     */
    private ArrayList<Argument> arguments = new ArrayList<Argument>();

    /**
     * Constructor for a discourse
     * @param n Name of the discourse
     * @param s The source from which the arguments are taken from
     * @param args A list of Argument objects
     */
    public Discourse(String n, Source s, ArrayList<Argument> args) {
        this.name = n;
        this.source = s;
        this.arguments = args;
    }

}
