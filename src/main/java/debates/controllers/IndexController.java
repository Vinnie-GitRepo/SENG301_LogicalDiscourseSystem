package debates.controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Class controlling the main index of the application, linking all the other controllers.
 */
public class IndexController {

    // Initialise the debate controllers.
    private ActorController actorController = new ActorController();
    private SourceController sourceController = new SourceController();
    private ArgumentController argumentController = new ArgumentController();
    private DiscourseController discourseController = new DiscourseController();
    private OrganisationController organisationController = new OrganisationController();
    private ArgumentLinkController argumentLinkController = new ArgumentLinkController();

    // Initialise String constants.
    private final String REGISTER_ORGANISATION = "1";
    private final String REGISTER_ACTOR = "2";
    private final String REGISTER_DISCOURSE = "3";
    private final String REGISTER_ARGUMENT = "4";
    private final String LINK_ARGUMENTS = "5";
    private final String EXIT_APPLICATION = "0";

    // Initialise int constant for successfully terminating the program.
    private final int EXIT_SUCCESS = 0;

    /**
     * Method handling the outermost level of the application, which lists the available features.
     * @param connection A non-null connection to the database.
     */
    public void presentHomePageOptions(Connection connection) throws SQLException {

        while (true) {

            System.out.println("\nWelcome to our debate system. Select a number from the options below \n");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            System.out.println("(1): Register an organisation. \n");
            System.out.println("(2): Register an actor. \n");
            System.out.println("(3): Register a discourse. \n");
            System.out.println("(4): Register an argument. \n");
            System.out.println("(5): Create a link between two arguments. \n");
            System.out.println("(0): Quit application. \n");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

            Scanner userInput = new Scanner(System.in);
            String choice = userInput.nextLine();

            if (choice.equals(REGISTER_ORGANISATION)) {
                organisationController.registerOrganisation(connection);
            } else if (choice.equals(REGISTER_ACTOR)) {
                actorController.registerActor(connection);
            } else if (choice.equals(REGISTER_DISCOURSE)) {
                discourseController.registerDiscourse(connection);
            } else if (choice.equals(REGISTER_ARGUMENT)) {
                argumentController.registerArgument(connection);
            } else if (choice.equals(LINK_ARGUMENTS)) {
                argumentLinkController.registerArgumentLink(connection);
            } else if (choice.equals(EXIT_APPLICATION)) {
                System.exit(EXIT_SUCCESS);
            } else {
                System.out.println("\n[INVALID] - Please select a valid option, which is a listed number with no spacing.");
            }
        }
    }

}
