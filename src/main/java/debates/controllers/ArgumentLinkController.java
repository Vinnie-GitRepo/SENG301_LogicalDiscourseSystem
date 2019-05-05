package debates.controllers;

import debates.models.Discourse;
import debates.repositories.ArgumentLinkRepository;
import debates.repositories.ArgumentRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ArgumentLinkController {

    /**
     * The constant for a user affirmation, used for yes/no questions.
     */
    private final String YES = "y";


    /**
     * The constant for a user refutation, used for yes/no questions.
     */
    private final String NO = "n";


    /**
     * The argumentRepository handling database-level operations for arguments.
     */
    private ArgumentRepository argumentRepository = new ArgumentRepository();

    /**
     * The argumentLinkRepository handling database-level operations for argument links.
     */
    private ArgumentLinkRepository argumentLinkRepository = new ArgumentLinkRepository();

    /**
     * The method controlling the command line aspect of argument link registration.
     * @param connection A non-null connection to the database.
     * @throws SQLException
     */
    public void registerArgumentLink(Connection connection) throws SQLException {

        // Ask user if they wish to register an argument link, scanning for a response.
        System.out.println("Would you like to register an argument link? (y/n)");
        Scanner userResponse = new Scanner(System.in);

        // Check the user input for a valid answer.
        try {
            String response = userResponse.nextLine();
            if (response.equals(YES)) {
                linkArguments(connection);
            } else if (response.equals(NO)) {
                //TODO: return to main selection
            } else {
                System.out.println("Your response must be a 'y' or a 'n'. Try again.");
                registerArgumentLink(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * The method handling the link of two arguments, providing a confirmation or error message depending on validity.
     * @param connection A non-null connection to the database.
     * @throws SQLException
     */
    public void linkArguments(Connection connection) throws SQLException {

        // Ask user to specify two arguments, then scan for responses.
        Scanner input = new Scanner(System.in);
        System.out.println(argumentRepository.getAllArguments(connection));
        System.out.println("Please select an argument from above");
        int arg1 = input.nextInt();
        System.out.println(argumentRepository.getAllArguments(connection));
        System.out.println("Please select another argument from above");
        int arg2 = input.nextInt();
        if (arg1 == arg2) {
            System.out.println("You cannot link an argument with itself. Please try again.");
            linkArguments(connection);
        } else {
            // Ask user to specify the type of argument link they would like to make.
            System.out.println("Would you like to link these arguments by (1) Support or (2) Contradiction?");
            int choice = input.nextInt();
            if (choice == 1) {
                // Link the two arguments.
                argumentLinkRepository.insertNewArgumentLink(connection, arg1, arg2);
            } else if (choice == 2) {
                // Check if the two arguments are part of the same discourse.
                // If they are, they cannot be linked. Else, create an argument link.
                if (argumentRepository.getDiscourseName(connection, arg1) == argumentRepository.getDiscourseName(connection, arg2)) {
                    System.out.println("You cannot contradict two arguments from the same discourse. Please try again.");
                    linkArguments(connection);
                } else {
                    argumentLinkRepository.insertNewArgumentLink(connection, arg1, arg2);
                }

            } else {
                System.out.println("That is not a valid choice. Please try again.");
                linkArguments(connection);
            }


        }

    }


}
