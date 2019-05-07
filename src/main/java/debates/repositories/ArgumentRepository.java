package debates.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class handling arguments at a database level.
 */
public class ArgumentRepository {

    public List<String> getAllArguments(Connection connection) throws SQLException {
        PreparedStatement argument = connection.prepareStatement("SELECT id, rephrasing FROM argument");
        ResultSet set = argument.executeQuery();
        argument.closeOnCompletion();
        List<String> arguments = new ArrayList<>();
        while (set.next()) {
            arguments.add(set.getString("id") + ": " + set.getString("rephrasing"));
        }
        return arguments;
    }

    public int getArgumentsLength(Connection connection) throws SQLException {
        PreparedStatement count = connection.prepareStatement("SELECT count(*) as count FROM argument");
        ResultSet set = count.executeQuery();
        count.closeOnCompletion();
        return set.getInt("count");
    }

    public String getDiscourseNameFromArgument(Connection connection, int argId) throws SQLException {
        // Query the database for the discourse that an argument of a given id belongs to.
        PreparedStatement discourse = connection.prepareStatement("SELECT discourse_name FROM argument WHERE id = ?");
        discourse.setInt(1, argId);
        ResultSet set = discourse.executeQuery();
        discourse.closeOnCompletion();

        return set.getString("discourse_name");
    }

    /**
     * @param connection A non-null connection to the database.
     * @param rephrasing
     * @param start Start indices of the argument in the discourse it comes from.
     * @param end End indices of the argument in the discourse it comes from.
     * @throws SQLException
     */
    public void insertNewArgument(Connection connection, String d_name, String rephrasing, int start, int end) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO argument(discourse_name, rephrasing, start_index, end_index) VALUES (?,?,?,?)");
        statement.setString(1, d_name);
        statement.setString(2, rephrasing);
        statement.setInt(3, start);
        statement.setInt(4, end);
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
        PreparedStatement check = connection.prepareStatement("SELECT * FROM argument WHERE start_index = ? AND end_index = ?");
        check.setInt(1, start);
        check.setInt(2, end);
        ResultSet set = check.executeQuery();
        check.closeOnCompletion();

        // If the query returns anything, then return true, else false.
        return (set.next() == true);
    }

}