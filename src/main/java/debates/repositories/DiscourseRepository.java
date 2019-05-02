package debates.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class handling discourses at a database level.
 */
public class DiscourseRepository {

    public void insertNewDiscourse(Connection connection, String name, String source) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO discourse(name, source) VALUE (?)");
        statement.setString(1, name);
        statement.executeUpdate();
        statement.closeOnCompletion();
    }

    /**
     * Method checking for the existence of a discourse within the database.
     * @param connection A non-null connection to the database.
     * @param newName The proposed name for a new discourse, which is being checked for uniqueness.
     * @return true if a discourse exists with the name being checked, false otherwise.
     * @throws SQLException
     */
    public boolean nameExists(Connection connection, String newName) throws SQLException {

        // Query the database for a discourse with a name equivalent to the new name being checked.
        PreparedStatement check = connection.prepareStatement("SELECT * FROM discourse WHERE name = ?");
        check.setString(1, newName);
        ResultSet set = check.executeQuery();
        check.closeOnCompletion();

        // If the query returns anything, then return true, else false.
        return (set.next() == true);
    }
}
