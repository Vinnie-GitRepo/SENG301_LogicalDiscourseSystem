package debates.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class handling arguments at a database level.
 */
public class ArgumentRepository {

    /**
     * @param connection A non-null connection to the database.
     * @param rephrasing
     * @param start Start indices of the argument in the discourse it comes from.
     * @param end End indices of the argument in the discourse it comes from.
     * @throws SQLException
     */
    public void insertNewArgument(Connection connection, String rephrasing, int start, int end) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO argument(rephrasing, start, end) VALUE (?)");
        statement.setString(1, rephrasing);
        statement.executeUpdate();
        statement.closeOnCompletion();
    }


    /**
     * Method checking for the existence of an argument within the database.
     * @param connection A non-null connection to the database.
     * @param start Start indices of the argument in the discourse it comes from.
     * @param start End indices of the argument in the discourse it comes from.
     * @return true if a source exists with the name being checked, false otherwise.
     * @throws SQLException
     */
    public boolean argumentExists(Connection connection, int start, int end) throws SQLException {

        // Query the database for a source with a name equivalent to the new name being checked.
        PreparedStatement check = connection.prepareStatement("SELECT * FROM argument WHERE startIndex = ? AND endIndex = ?");
        check.setInt(1, start);
        check.setInt(2, end);
        ResultSet set = check.executeQuery();
        check.closeOnCompletion();

        // If the query returns anything, then return true, else false.
        return (set.next() == true);
    }

}
