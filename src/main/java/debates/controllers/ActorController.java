package debates.controllers;

import debates.models.Actor;
import debates.models.Affiliation;
import debates.models.Organisation;
import debates.repositories.ActorRepository;
import debates.repositories.OrganisationRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
     * Index controller linking the this feature back to the home page.
     */
//    private IndexController index = new IndexController();


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

            while (!(response.equals(YES) || response.equals(NO))) {
                System.out.println("Your response must be a 'y' or a 'n'. Try again.");
                response = userResponse.nextLine();
            }

            if (response.equals(YES)) {
                nameActor(connection);
            } else if (response.equals(NO)) {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
//            registerActor(connection);
            return;
        }
    }


    /**
     * Method handling the naming of new actors.
     * @param connection A non-null connection to the database.
     * @throws SQLException The exception thrown if any issues occur when working with the database.
     */
    public void nameActor(Connection connection) throws SQLException {

        // Ask user to give a first name for an actor, then scan for a response.
        System.out.println("Please provide a first name.");
        Scanner inputFirstName = new Scanner(System.in);
        String firstName = inputFirstName.nextLine();

        // Ask user to give a last name for an actor, then scan for a response.
        System.out.println("Please provide a last name.");
        Scanner inputLastName = new Scanner(System.in);
        String lastName = inputLastName.nextLine();

        Actor actor = new Actor(firstName, lastName);

        // Ask user to add affiliations.
        registerAffiliation(connection, actor);

        // Check if the names of the new actor match those of any actors in the database
        if (actorRepository.isHomonym(connection, firstName, lastName)) {
            provideExistingActorDetails(connection, actor);
            boolean isConfirmed = getConfirmation();
            if (isConfirmed) {
                actorRepository.insertNewActor(connection, actor);
            } else {
                return;
            }
        } else {
            actorRepository.insertNewActor(connection, actor);
        }
    }



    /**
     * Method handling the registration of an actor's affiliation with an organisation.
     * @param connection A non-null connection to the database.
     * @throws SQLException The exception thrown if any issues occur when working with the database.
     */
    public void registerAffiliation(Connection connection, Actor actor) throws SQLException {

        boolean doneRegisteringAffiliations = false;

        while (!doneRegisteringAffiliations) {

            System.out.println("Would you like to register an affiliation? (y/n)");
            Scanner userResponse = new Scanner(System.in);
            String response = userResponse.nextLine();

            // Check the user input for a valid answer.
            if (response.equals(YES)) {
                // Get the organisation of the affiliation from the user.
                System.out.println("Provide the organisation affiliated with the actor. (Optional)");
                Scanner affiliationOrganisation = new Scanner(System.in);
                String organisationText = affiliationOrganisation.nextLine();

                // Get the actor's role within the organisation from the user.
                System.out.println("Provide the roll occupied by the actor. (Optional)");
                Scanner affiliationRole =  new Scanner(System.in);
                String roleText = affiliationRole.nextLine();

                // Get the start date for an actor affiliation.
                Date startDate = affiliationDateProcedure(START_DATE);

                // Get the end date for a user an actor affiliation.
                Date endDate = affiliationDateProcedure(END_DATE);

                // Determine if the organisation exists, and if it does use that or else register a new organisation.
                if (organisationRepository.nameExists(connection, organisationText)) {
                    Organisation organisation = organisationRepository.retrieveOrganisation(connection, organisationText);
                    actor.insertAffiliation(roleText, startDate, endDate, organisation);
                } else {
                    actor.insertAffiliation(roleText, startDate, endDate, organisationText);
                }
            } else if (response.equals(NO)) {
                doneRegisteringAffiliations = true;
            } else {
//                System.out.println("Your response must be a 'y' or a 'n'. Try again.");
//                registerAffiliation(connection, actor);
                return;
            }
        }


    }



    /**
     * Method printing a list of already registered actors in the database whose names are the same as a new actor.
     * @param connection A non-null connection to the database.
     */
    public void provideExistingActorDetails(Connection connection, Actor actor) throws SQLException {

        List<Actor> actors = actorRepository.retrieveHomonymActors(connection, actor);

    }


    /**
     * Method handling the user confirmation aspect of registering a homonym actor.
     * @return true if the user enters 'y', and false if the user enters 'n'. Otherwise the function calls itself.
     */
    public boolean getConfirmation() {
        System.out.println("Do you still wish to add this actor? (y/n)");
        Scanner userInput = new Scanner(System.in);
        String response = userInput.nextLine();

        boolean result = true;

//        if (response.equals(YES)) {
//            result = true;
//        } else if (response.equals(NO)) {
//            result = false;
//        } else {
//            System.out.println("Your response must be a 'y' or a 'n'. Try again.");
//            getConfirmation();
//        }

        while (!(response.equals(YES) || response.equals(NO))) {
            System.out.println("Your response must be a 'y' or a 'n'. Try again.");
            response = userInput.nextLine();
        }

        if (response.equals(YES)) {
            result = true;
        } else if (response.equals(NO)) {
            result = false;
        }

        return result;
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
