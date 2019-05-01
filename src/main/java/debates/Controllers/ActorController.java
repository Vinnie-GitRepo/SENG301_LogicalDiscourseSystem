package debates.Controllers;

import debates.Repositories.ActorRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Class controlling actors at a business-logic level.
 */
public class ActorController {

    /**
     * The constant for a user affirmation, used for yes/no questions.
     */
    private final String YES = "y";


    /**
     * The constant for a user refutation, used for yes/no questions.
     */
    private final String NO = "n";


    /**
     * The repository handling database-level operations for actors.
     */
    private ActorRepository repository = new ActorRepository();


    /**
     * Method controlling the command line aspect of actor registration.
     * @param connection A non-null connection to the database.
     */
    public void registerActor(Connection connection) {
        // Ask a user if they wish to register an actor, scanning for a response.
        System.out.println("Would you like to register an actor? (y/n)");
        Scanner userResponse = new Scanner(System.in);

        // Check the user input for a valid answer
        try {
            String response = userResponse.nextLine();
            if (response.equals(YES)) {
                nameActor(connection);
            } else if (response.equals(NO)) {
                //TODO: return to main selection
            } else {
                System.out.println("Your response must be a 'y' or a 'n'. Try again.");
                registerActor(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
            registerActor(connection);
        }
    }


    /**
     * Method handling the naming of actors
     * @param connection
     * @throws SQLException
     */
    public void nameActor(Connection connection) throws SQLException {
        // Ask user to give a first name for an actor, then scan for a response.
        System.out.println("Please provide a first name.");
        Scanner inputFirstName = new Scanner(System.in);
        String firstName = inputFirstName.nextLine();


        // Ask user to give a last name for an actor, then scan for a response.
        System.out.println("Please provide a first name.");
        Scanner inputLastName = new Scanner(System.in);
        String lastName = inputLastName.nextLine();



        // Ask user to add affiliations.
        registerAffiliation(connection);





        // Check is the names of the new actor match those of any actors in the database
        if (repository.isHomonym(connection, firstName, lastName)) {
            provideExistingActorDetails();
            boolean isConfirmed = getConfirmation();
            if (isConfirmed) {

                repository.insertNewActor(connection, actor);
            }
        } else {

        }
    }


    /**
     *
     * @param connection
     */
    public void registerAffiliation(Connection connection) {

        System.out.println("Would you like to register an affiliation?");
        Scanner userResponse = new Scanner(System.in);

        // Check the user input for a valid answer

        String response = userResponse.nextLine();
        if (response.equals(YES)) {

            System.out.println("Provide the roll occupied by the actor (Optional)");
            Scanner affiliationRole =  new Scanner(System.in);

            System.out.println("Provide the organisation affiliated with the actor (Optional)");
            Scanner affiliationOrganisation = new Scanner(System.in);

            System.out.println("Provide the starting date ");


        } else if (response.equals(NO)) {

        } else {
            System.out.println("Your response must be a 'y' or a 'n'. Try again.");
            registerAffiliation(connection);
        }


    }




}
