package debates.models;

/**
 * A unique entity which may be affiliated with any number of actors.
 */
public class Organisation {

    /**
     * The name of an organisation, which must be unique, so it is the distinguishing attribute of this class.
     */
    private String name;


    /**
     * The constructor for an organisation.
     * @param name the unique name of the organisation being generated.
     */
    public Organisation(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
