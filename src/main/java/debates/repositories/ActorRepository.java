package debates.repositories;

import debates.models.Actor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class handling actors at a database level.
 */
public class ActorRepository {

    /**
     * Checks if an actor within the database has the same names as a new actor being created.
     * Used when naming a new actor being registered, determining whether a confirmation message needs displaying.
     * @param connection The connection to the database.
     * @param firstName The new first name being compared with existing actors through a query.
     * @param lastName The new last name being compared with existing actors through a query.
     * @return true if an existing actor has the same names, or false otherwise.
     * @throws SQLException
     */
    public boolean isHomonym(Connection connection, String firstName, String lastName) throws SQLException {

        // Query the database for an actor with names equivalent to those of the new actor being registered.
        PreparedStatement check = connection.prepareStatement("SELECT * FROM actor WHERE fname = ? AND lname = ?");
        check.setString(1, firstName);
        check.setString(2, lastName);
        ResultSet set = check.executeQuery();
        check.closeOnCompletion();

        // If the query returns anything, then return true, else false.
        return (set.next() == true);
    }


    /**
     * Inserts a new actor into the database
     * @param connection A non-null connection to the database.
     * @param actor The actor being inserted into the database.
     * @throws SQLException
     */
    public void insertNewActor(Connection connection, Actor actor) throws SQLException {

    }

}
