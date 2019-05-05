package debates.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class handling sources at a database level.
 */
public class SourceRepository {

    /**
     * Inserts a new source into the database.
     * @param connection A non-null connection to the database.
     * @param name Name for a new source.
     * @throws SQLException
     */
    public void insertNewSource(Connection connection, String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO source(name) VALUE (?)");
        statement.setString(1, name);
        statement.executeUpdate();
        statement.closeOnCompletion();
    }


    /**
     * Method checking for the existence of a name within the database.
     * @param connection A non-null connection to the database.
     * @param name The name of a source.
     * @return true if a source exists with the name being checked, false otherwise.
     * @throws SQLException
     */
    public boolean nameExists(Connection connection, String name) throws SQLException {

        // Query the database for a source with a name equivalent to the new name being checked.
        PreparedStatement check = connection.prepareStatement("SELECT * FROM source WHERE name = ?");
        check.setString(1, name);
        ResultSet set = check.executeQuery();
        check.closeOnCompletion();

        // If the query returns anything, then return true, else false.
        return (set.next() == true);
    }


}
