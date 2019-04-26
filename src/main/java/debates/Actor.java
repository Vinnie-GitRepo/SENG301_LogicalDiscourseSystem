package debates;

import java.util.ArrayList;
import java.util.Date;

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


    private ArrayList<Affiliation> affiliations = new ArrayList<Affiliation>();

    public Actor (String fname, String lname) {
        this.firstname = fname;
        this.lastname = lname;
    }

    public Actor (String fname, String lname, double trust) {
        this.firstname = fname;
        this.lastname = lname;
        this.levelOfTrust = trust;
    }

    public void insertAffiliation() {
        affiliations.add(new Affiliation());
    }

    public void insertAffiliation(String role, Date start, Date end, Organisation org) {
        affiliations.add(new Affiliation(role, start, end, org));
    }


}
