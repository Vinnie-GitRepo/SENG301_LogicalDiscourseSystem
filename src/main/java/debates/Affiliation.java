package debates;

import java.util.Date;

/**
 * A link between an Actor and an Organisation.
 */
public class Affiliation {

    private String role;
    private Date startDate;
    private Date endDate;
    private Organisation organisation;


    public Affiliation() {
    }

    public Affiliation(String r, Date start, Date end, Organisation org) {
        this.role = r;
        this.startDate = start;
        this.endDate = end;
        this.organisation = org;
    }
}
