package debates.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

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

}
