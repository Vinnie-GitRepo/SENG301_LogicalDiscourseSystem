package debates.Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class OrganisationController {

    /**
     * The constant for a user affirmation, used for yes/no questions.
     */
    private final String YES = "y";


    /**
     * The constant for a user refutation, used for yes/no questions.
     */
    private final String NO = "n";


    /**
     * The method controlling the command line aspect of organisation registration.
     */
    public void registerOrganisation(Connection connection) throws SQLException {
        // Ask user if they wish to register an organisation.
        System.out.println("Would you like to register an organisation? (y/n)");
        Scanner inputResponse = new Scanner(System.in);

        //
        try {
            String response = inputResponse.nextLine();
            if (response.equals(YES)) {
                nameOrganisation(connection);
            } else if (response.equals(NO)) {
                //TODO: return to main selection
            } else {
                System.out.println("Your response must be a 'y' or a 'n'. Try again.");
                registerOrganisation(connection);
            }
        } catch (Exception e) {

        }
    }


    /**
     * The method handling the naming of a new organisation, providing a confirmation or error message depending on validity.
     * @param connection A non-null connection to the database.
     * @throws SQLException
     */
    public void nameOrganisation(Connection connection) throws SQLException {
        System.out.println("Please provide the organisation name.");
        Scanner inputName = new Scanner(System.in);
        String newName = inputName.nextLine();
        if (!nameExists(connection, newName)) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO organisation(name) VALUE (?)");
            statement.setString(1, newName);
            statement.executeUpdate();
            statement.closeOnCompletion();
            System.out.println("The organisation, " + newName + ", has been added successfully.");
        } else if (nameExists(connection, newName)) {
            System.out.println("The organisation, " + newName + ", already exists within the database. Please enter another name.");
            nameOrganisation(connection);
        } else {
            System.out.println("");
        }
    }


    /**
     *
     * @param connection The connection to the database.
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
