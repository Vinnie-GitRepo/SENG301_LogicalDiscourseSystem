package debates.controllers;

import debates.models.Affiliation;
import debates.repositories.ActorRepository;
import debates.repositories.OrganisationRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
     * A constant for informing the user about what affiliation date they're entering.
     */
    private final String START_DATE = "starting";


    /**
     * A constant for informing the user about what affiliation date they're entering.
     */
    private final String END_DATE = "end";


    /**
     * The repository handling database-level operations for actors.
     */
    private ActorRepository actorRepository = new ActorRepository();


    /**
     * The repository handling database-level operations for organisations.
     */
    private OrganisationRepository organisationRepository = new OrganisationRepository();


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
        if (actorRepository.isHomonym(connection, firstName, lastName)) {
            provideExistingActorDetails();
            boolean isConfirmed = getConfirmation();
            if (isConfirmed) {

                actorRepository.insertNewActor(connection, actor);
            }
        } else {

        }
    }


    /**
     * Method handling the registration of an actor's affiliation with an organisation.
     * @param connection A non-null connnection to the database.
     */
    public void registerAffiliation(Connection connection) {

        System.out.println("Would you like to register an affiliation?");
        Scanner userResponse = new Scanner(System.in);
        String response = userResponse.nextLine();




        // Check the user input for a valid answer.
        if (response.equals(YES)) {

            Affiliation affiliation = new Affiliation();

            System.out.println("Provide the roll occupied by the actor. (Optional)");
            Scanner affiliationRole =  new Scanner(System.in);
            String roleText = affiliationRole.nextLine();



            System.out.println("Provide the organisation affiliated with the actor. (Optional)");
            Scanner affiliationOrganisation = new Scanner(System.in);
            String organisationText = affiliationOrganisation.nextLine();

            repository.name


            // Get the start date for a user affiliation.
            Date startDate = affiliationDateProcedure(START_DATE);

            // Get the end date for a user affiliation.
            Date endDate = affiliationDateProcedure(END_DATE);






        } else if (response.equals(NO)) {

        } else {
            System.out.println("Your response must be a 'y' or a 'n'. Try again.");
            registerAffiliation(connection);
        }
    }


    /**
     * Method handling the creation a Date objects for use in affiliations.
     * Used for creating both the starting date and end date of a new affiliation.
     * @param printString The worded context for the command line message. Should only be "starting" or "end"
     * @return a Date object to set as an Affiliation attribute.
     */
    public Date affiliationDateProcedure(String printString) {

        // Ask user for a date
        System.out.println("Provide the " + printString + " date of the affiliation, in DD/MM/YYYY format. (Optional)");
        Scanner affiliationDate = new Scanner(System.in);
        String dateText = affiliationDate.nextLine();
        Date date = null;

        // Determine whether the date should remain null, and try to parse if an entry is detected.
        if (!dateText.equals("")) {
            try {
                date = new SimpleDateFormat("dd/MM/yyyy").parse(dateText);
            } catch (java.text.ParseException e) {
                System.out.println("We couldn't process your input, so the date will remain null");
            }
        }

        return date;
    }






}
