package debates.Models;

/**
 * A link between two Argument objects
 */
public class ArgumentLink {

    /**
     * One argument to be linked with
     */
    private Argument argument1;

    /**
     * The other argument to be linked with
     */
    private Argument argument2;

    /**
     * String indicating the type of link between two arguments: Contradiction, Support
     */
    private String type;

    /**
     * Constructor for an argument link
     * @param arg1 One argument to be linked with
     * @param arg2 The other argument to be linked with
     * @param t
     */
    public ArgumentLink(Argument arg1, Argument arg2, String t) {
        this.argument1 = arg1;
        this.argument2 = arg2;
        this.type = t;
    }

}
