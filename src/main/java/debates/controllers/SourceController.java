package debates.controllers;

import debates.repositories.SourceRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Class controlling sources at a business-logic level.
 */
public class SourceController {

    /**
     * The constant for a user affirmation, used for yes/no questions.
     */
    private final String YES = "y";


    /**
     * The constant for a user refutation, used for yes/no questions.
     */
    private final String NO = "n";


    /**
     * Index controller linking the this feature back to the home page.
     */
    private IndexController index = new IndexController();


    /**
     * The repository handling database-level operations for organisations.
     */
    private SourceRepository repository = new SourceRepository();


    /**
     * The method controlling the command line aspect of source registration.
     * @param connection A non-null connection to the database.
     * @throws SQLException
     */
    public void registerSource(Connection connection) throws SQLException {

        // Ask user if they wish to register a source, scanning for a response.
        System.out.println("Would you like to register a source? (y/n)");
        Scanner userResponse = new Scanner(System.in);

        // Check the user input for a valid answer.
        try {
            String response = userResponse.nextLine();
            if (response.equals(YES)) {
                nameSource(connection);
            } else if (response.equals(NO)) {
                //TODO: return to main selection
            } else {
                System.out.println("Your response must be a 'y' or a 'n'. Try again.");
                registerSource(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * The method handling the naming of a new source, providing a confirmation or error message depending on validity.
     * @param connection A non-null connection to the database.
     * @throws SQLException
     */
    public void nameSource(Connection connection) throws SQLException {

        // Ask user to name an organisation, then scan for a response.
        System.out.println("Please provide the source name.");
        Scanner inputName = new Scanner(System.in);
        String newName = inputName.nextLine();

        // Check the user input against the acceptance criteria.
        if (!repository.nameExists(connection, newName)) {
            repository.insertNewSource(connection, newName);
            System.out.println("The source, " + newName + ", has been added successfully.");
        } else if (repository.nameExists(connection, newName)) {
            System.out.println("The source, " + newName + ", already exists within the database. Please enter another name.");
            nameSource(connection);
        } else {
            System.out.println("There was an issue with your input. Please try again.");
            nameSource(connection);
        }
    }

}
