package debates.controllers;

import debates.repositories.DiscourseRepository;
import debates.repositories.SourceRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Class controlling discourses at a business-logic level.
 */
public class DiscourseController {

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
     * The discourseRepository handling database-level operations for discourses.
     */
    private DiscourseRepository discourseRepository = new DiscourseRepository();


    /**
     * The sourceRepository handling database-level operations for sources.
     */
    private SourceRepository sourceRepository = new SourceRepository();


    /**
     * Method controlling the command line aspect of discourse creation.
     * @param connection A non-null connection to the database.
     */
    public void registerDiscourse(Connection connection) {
        // Ask a user if they wish to create a discourse, scanning for a response.
        System.out.println("Would you like to create a discourse? (y/n)");
        Scanner userResponse = new Scanner(System.in);

        // Check the user input for a valid answer
        try {
            String response = userResponse.nextLine();
            if (response.equals(YES)) {
                nameDiscourse(connection);
            } else if (response.equals(NO)) {
                //TODO: return to main selection
            } else {
                System.out.println("Your response must be a 'y' or a 'n'. Try again.");
                registerDiscourse(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
            registerDiscourse(connection);
        }
    }

    /**
     * The method handling the naming of a new discourse, providing a confirmation or error message depending on validity.
     * @param connection A non-null connection to the database.
     * @throws SQLException
     */
    public void nameDiscourse(Connection connection) throws SQLException {

        // Ask user to name a discourse, then scan for a response.
        System.out.println("Please provide the discourse name.");
        Scanner inputName = new Scanner(System.in);
        String newName = inputName.nextLine();

        // Ask user to name an existing source, then scan for a response
        String sourceName = inputName.nextLine();

        // Check the user input against the acceptance criteria.
        if (!discourseRepository.nameExists(connection, newName) && sourceRepository.nameExists(connection, sourceName)) {
            discourseRepository.insertNewDiscourse(connection, newName, sourceName);
            System.out.println("The discourse, " + newName + ", has been added successfully.");
        } else if (discourseRepository.nameExists(connection, newName)) {
            System.out.println("The discourse, " + newName + ", already exists within the database. Please enter another name.");
            nameDiscourse(connection);
        } else {
            System.out.println("There was an issue with your input. Please try again.");
            nameDiscourse(connection);
        }
    }

}
