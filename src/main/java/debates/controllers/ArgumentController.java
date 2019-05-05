package debates.controllers;



import debates.repositories.ArgumentRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Class controlling arguments at a business-logic level.
 */
public class ArgumentController {

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
    private ArgumentRepository repository = new ArgumentRepository();


    /**
     * The method controlling the command line aspect of argument registration.
     * @param connection A non-null connection to the database.
     * @throws SQLException
     */
    public void registerArgument(Connection connection) throws SQLException {

        // Ask user if they wish to register an argument, scanning for a response.
        System.out.println("Would you like to register an argument? (y/n)");
        Scanner userResponse = new Scanner(System.in);

        // Check the user input for a valid answer.
        try {
            String response = userResponse.nextLine();
            if (response.equals(YES)) {
                createArgument(connection);
            } else if (response.equals(NO)) {
                //TODO: return to main selection
            } else {
                System.out.println("Your response must be a 'y' or a 'n'. Try again.");
                registerArgument(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * The method handling the creation of a new argument, providing a confirmation or error message depending on validity.
     * @param connection A non-null connection to the database.
     * @throws SQLException
     */
    public void createArgument(Connection connection) throws SQLException {

//        // Ask user to name an organisation, then scan for a response.
//        System.out.println("Please type an argument.");
//        Scanner input = new Scanner(System.in);
//        String argument = input.nextLine();
//
//
//        // Check the user input against the acceptance criteria.
//        if (!repository.argumentExists(connection, newName)) {
//            repository.insertNewArgument(connection, newName);
//            System.out.println("The argument, " + newName + ", has been added successfully.");
//        } else if (repository.argumentExists(connection, newName)) {
//            System.out.println("The argument, " + newName + ", already exists within the database. Please enter another name.");
//            createArgument(connection);
//        } else {
//            System.out.println("There was an issue with your input. Please try again.");
//            createArgument(connection);
//        }
    }

}
