package debates;

/**
 * A person who pronounces any number of discourses and, may be affiliated with any number of organisations.
 */
public class Actor {

    /**
     * The first name of an actor.
     * This is used to help distinguish different actors, and will be used in homonym checking.
     */
    private String firstname;


    /**
     * The last name of an actor.
     * This is used to help distinguish different actors, and will be used in homonym checking.
     */
    private String lastname;


    /**
     * The calculated quantification of an actor's credibility.
     * The value is the sum of the confidence levels of all the arguments and sources contributed by the actor.
     */
    private double levelOfTrust;

}
