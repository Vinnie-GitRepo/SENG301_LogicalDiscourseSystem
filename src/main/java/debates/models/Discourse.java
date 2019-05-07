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
     * The text comprising the discourse
     */
    private String text;

    /**
     * Constructor for a discourse
     * @param n Name of the discourse
     * @param s The source from which the arguments are taken from
     * @param t The text comprising the discourse
     */
    public Discourse(String n, Source s, String t) {
        this.name = n;
        this.source = s;
        this.text = t;
    }

    public String getName() { return name; }

    public String getText() {
        return text;
    }

}
