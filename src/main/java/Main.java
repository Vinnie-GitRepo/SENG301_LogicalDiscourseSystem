import debates.controllers.IndexController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The class running the debate system.
 */
public class Main {

    /**
     * The main procedure running the command line application.
     * Establish database connection to run the program from the home page, or present an error upon failure.
     * @param args The array of String arguments
     */
    public static void main(String[] args) {
        String url = "";
        try (Connection connection = DriverManager.getConnection(url)) {
            IndexController index = new IndexController();
            index.presentHomePageOptions(connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
