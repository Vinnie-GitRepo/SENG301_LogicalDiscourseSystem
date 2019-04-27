package debates.Models;

import java.util.Date;

/**
 * A link between an Actor and an Organisation.
 */
public class Affiliation {

    /**
     * The current or last role of the actor within the organisation they're affiliated with.
     * This field can be null as all fields are optional.
     */
    private String role;


    /**
     * The first day of the actor's affiliation with an organisation.
     * This field can be null as all fields are optional.
     */
    private Date startDate;


    /**
     * The last day of the actor's affiliation with an organisation.
     * This field can be null as all fields are optional.
     */
    private Date endDate;


    /**
     * The organisation the actor is affiliated with.
     * This field can be null as all fields are optional.
     */
    private Organisation organisation;


    /**
     * Affiliation constructor for if the organisation is not registered within the database.
     * All parameters can be null.
     * @param role The last/current role the actor had within the organisation when affiliated with them.
     * @param start The start date of the actor's affiliation with the organisation.
     * @param end The end date of the actor's affiliation with the organisation.
     * @param name The name of the organisation the actor was affiliated with.
     */
    public Affiliation(String role, Date start, Date end, String name) {
        this.role = role;
        this.startDate = start;
        this.endDate = end;
        this.organisation = new Organisation(name);
    }


    /**
     * Affiliation constructor for if the organisation is registered within the database
     * All parameters can be null.
     * @param role The last/current role the actor had within the organisation when affiliated with them.
     * @param start The start date of the actor's affiliation with the organisation.
     * @param end The end date of the actor's affiliation with the organisation.
     * @param org The name of the organisation the actor was affiliated with.
     */
    public Affiliation(String role, Date start, Date end, Organisation org) {
        this.role = role;
        this.startDate = start;
        this.endDate = end;
        this.organisation = org;
    }
}