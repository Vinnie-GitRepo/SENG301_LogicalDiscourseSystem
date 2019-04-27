package debates.Models;

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
     * The list of organisations the actor is affiliated with.
     */
    private ArrayList<Affiliation> affiliations = new ArrayList<Affiliation>();


    /**
     * Constructor for the actor which does not include the level of trust.
     * @param fname
     * @param lname
     */
    public Actor (String fname, String lname) {
        this.firstname = fname;
        this.lastname = lname;
    }


    /**
     * Constructor for the actor which does include the level of trust.
     * @param fname
     * @param lname
     * @param trust
     */
    public Actor (String fname, String lname, double trust) {
        this.firstname = fname;
        this.lastname = lname;
    }


    /**
     * Insert an affiliation for an actor where the organisation is not yet registered within the database.
     * @param role
     * @param start
     * @param end
     * @param newOrg
     */
    public void insertAffiliation(String role, Date start, Date end, String newOrg) {
        affiliations.add(new Affiliation(role, start, end, newOrg));
    }


    /**
     * Insert an affiliation for an actor where the organisation is registered within the database.
     * @param role
     * @param start
     * @param end
     * @param org
     */
    public void insertAffiliation(String role, Date start, Date end, Organisation org) {
        affiliations.add(new Affiliation(role, start, end, org));
    }


}
