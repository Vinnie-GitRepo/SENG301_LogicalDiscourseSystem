package debates.controllers;

import debates.repositories.OrganisationRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Class controlling organisations at a business-logic level.
 */
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
     * Index controller linking the this feature back to the home page.
     */
    private IndexController index = new IndexController();


    /**
     * The repository handling database-level operations for organisations.
     */
    private OrganisationRepository repository = new OrganisationRepository();


    /**
     * The method controlling the command line aspect of organisation registration.
     * @param connection A non-null connection to the database.
     * @throws SQLException
     */
    public void registerOrganisation(Connection connection) throws SQLException {

        // Ask user if they wish to register an organisation, scanning for a response.
        System.out.println("Would you like to register an organisation? (y/n)");
        Scanner userResponse = new Scanner(System.in);

        // Check the user input for a valid answer.
        try {
            String response = userResponse.nextLine();
            if (response.equals(YES)) {
                nameOrganisation(connection);
            } else if (response.equals(NO)) {
                //TODO: return to main selection
            } else {
                System.out.println("Your response must be a 'y' or a 'n'. Try again.");
                registerOrganisation(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * The method handling the naming of a new organisation, providing a confirmation or error message depending on validity.
     * @param connection A non-null connection to the database.
     * @throws SQLException
     */
    public void nameOrganisation(Connection connection) throws SQLException {

        // Ask user to name an organisation, then scan for a response.
        System.out.println("Please provide the organisation name.");
        Scanner inputName = new Scanner(System.in);
        String newName = inputName.nextLine();

        // Check the user input against the acceptance criteria.
        if (!repository.nameExists(connection, newName)) {
            repository.insertNewOrganisation(connection, newName);
            System.out.println("The organisation, " + newName + ", has been added successfully.");
        } else if (repository.nameExists(connection, newName)) {
            System.out.println("The organisation, " + newName + ", already exists within the database. Please enter another name.");
            nameOrganisation(connection);
        } else {
            System.out.println("There was an issue with your input. Please try again.");
            nameOrganisation(connection);
        }
    }

}
