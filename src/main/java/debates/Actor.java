package debates;

import java.util.ArrayList;
import java.util.Date;

/**
 * A person who pronounces any number of discourses and, may be affiliated with any number of organisations.
 */
public class Actor {

    private String firstname;
    private String lastname;
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
