package debates.repositories;

import debates.models.Discourse;
import debates.models.Source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArgumentLinkRepository {

    public void insertNewArgumentLink(Connection connection, int arg1, int arg2, String link) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO argument_link VALUES (?,?,?)");
        statement.setInt(1, arg1);
        statement.setInt(2, arg2);
        statement.setString(3, link);
        statement.executeUpdate();
        statement.closeOnCompletion();
    }

    public boolean argumentLinkExists(Connection connection, int arg1, int arg2) throws SQLException {

        // Query the database for a source with a name equivalent to the new name being checked.
        PreparedStatement check = connection.prepareStatement("SELECT * FROM argument_link WHERE argument_1 = ? AND argument_2 = ?");
        check.setInt(1, arg1);
        check.setInt(2, arg2);
        ResultSet set = check.executeQuery();
        check.closeOnCompletion();

        // If the query returns anything, then return true, else false.
        return (set.next() == true);
    }

}
