package debates.Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class handling organisations at a database level.
 */
public class OrganisationRepository {

    public void insertNewOrganisation(Connection connection, String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO organisation(name) VALUE (?)");
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
     * @throws SQLException
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

}
