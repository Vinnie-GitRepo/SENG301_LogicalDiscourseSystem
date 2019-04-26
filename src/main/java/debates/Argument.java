package debates;

/**
 * A rephrasing of a sentence related to a Discourse object, created by taking unique slices of a Discourse object.
 */
public class Argument {

    /**
     * The string obtained from a slice of Discourse object's text, using indexes.
     */
    private String rephrasing;


    /**
     * The start index of a Discourse object's text slice, from which the rephrasing will be obtained.
     */
    private int startIndex;


    /**
     * The end index of a Discourse object's text slice, from which the rephrasing will be obtained.
     */
    private int endIndex;


    /**
     * The calculated level of validity associated with an argument.
     * This value will be used when calculating the level of trust associated with an actor.
     */
    private double confidenceLevel;


    public Argument(String re, int start, int end) {
        this.rephrasing = re;
        this.startIndex = start;
        this.endIndex = end;
    }

}
