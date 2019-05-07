package debates.repositories;

import debates.models.Organisation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class handling organisations at a database level.
 */
public class OrganisationRepository {

    /**
     * Inserts a new organisation into the database.
     * @param connection A non-null connection to the database.
     * @param name The name of the new organisation being inserted.
     * @throws SQLException The exception thrown if any issues occur when working with the database.
     */
    public void insertNewOrganisation(Connection connection, String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO organisation VALUES (?)");
        statement.setString(1, name);
        statement.executeUpdate();
        statement.closeOnCompletion();
    }


    /**
     * Method checking for the existence of a name within the database.
     * Used to determine whether a new actor is a homonym actor.
     * @param connection A non-null connection to the database.
     * @param newName The proposed name for a new organisation, which is being checked for uniqueness.
     * @return true if an organisation exists with the name being checked, false otherwise.
     * @throws SQLException The exception thrown if any issues occur when working with the database.
     */
    public boolean nameExists(Connection connection, String newName) throws SQLException {

        // Query the database for an organisation with a name equivalent to the new name being checked.
        PreparedStatement check = connection.prepareStatement("SELECT * FROM organisation WHERE name = ?");
        check.setString(1, newName);
        ResultSet set = check.executeQuery();
        check.closeOnCompletion();

        // If the query returns anything, then return true, else false.
        return (set.next() == true);
    }


    /**
     * Method used to retrieve an organisation by querying the database using name as a search parameter.
     * @param connection A non-null connection to the database.
     * @param name The name of the organisation we're attempting to retrieve.
     * @return An Organisation object correcponding to the name parameter.
     * @throws SQLException The exception thrown if any issues occur when working with the database.
     */
    public Organisation retrieveOrganisation(Connection connection, String name) throws SQLException {

        // Query the database for an organisation with a name equivalent ot the new name being checked.
        PreparedStatement nameQuery = connection.prepareStatement("SELECT name FROM organisation WHERE name = ?");
        nameQuery.setString(1, name);
        String orgName = nameQuery.executeQuery().toString();
        nameQuery.closeOnCompletion();

        // Return the organisation found.
        return new Organisation(orgName);
    }

}
