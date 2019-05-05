package debates.repositories;

import debates.models.Discourse;
import debates.models.Source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArgumentLinkRepository {

    public void insertNewArgumentLink(Connection connection, int arg1, int arg2) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO argument_link(arg1, arg2) VALUE (?)");
        statement.setInt(1, arg1);
        statement.setInt(2, arg2);
        statement.executeUpdate();
        statement.closeOnCompletion();
    }

}
