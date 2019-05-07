package debates.controllers;

import debates.repositories.ArgumentLinkRepository;
import debates.repositories.ArgumentRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
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

            while (!(response.equals(YES) || response.equals(NO))) {
                System.out.println("Your response must be a 'y' or a 'n'. Try again.");
                response = userResponse.nextLine();
            }

            if (response.equals(YES)) {
                linkArguments(connection);
            } else if (response.equals(NO)) {
                //TODO: return to main selection
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

        // Ask user to specify an argument (after listing all arguments), then scan for responses.
        Scanner input = new Scanner(System.in);
        System.out.println(argumentRepository.getAllArguments(connection));
        System.out.println("Please select an argument from above (enter its number).");
        int arg1 = 0;
        try {
            arg1 = input.nextInt();
            while (arg1 > argumentRepository.getArgumentsLength(connection) || arg1 < 1) {
                System.out.println("That number is out of range. Please try again.");
                arg1 = input.nextInt();
            }
        } catch (InputMismatchException e) {
            System.out.println("You may only enter numbers.");
            return;
        }

        // Ask user to specify another argument (after listing all arguments), then scan for responses.
        System.out.println(argumentRepository.getAllArguments(connection));
        System.out.println("Please select another argument from above (enter its number).");
        int arg2 = 0;
        try {
            arg2 = input.nextInt();
            while (arg2 > argumentRepository.getArgumentsLength(connection) || arg2 < 1 || arg2 == arg1) {
                System.out.println("That number is out of range or matches the previous argument. Please try again.");
                arg2 = input.nextInt();
            }
        } catch (InputMismatchException e) {
            System.out.println("You may only enter numbers.");
            return;
        }

        // Check that the pair of provided argument id's does not already exist in the argument link database.
        if (argumentLinkRepository.argumentLinkExists(connection, arg1, arg2) || argumentLinkRepository.argumentLinkExists(connection, arg2, arg1)) {
            System.out.println("A link already exists between these two arguments.");
            return;
        }

        // Ask user to specify the type of argument link they would like to make.
        System.out.println("Would you like to link these arguments by (1) Support or (2) Contradiction?");
        int choice = input.nextInt();
        if (choice == 1) {
            // Link the two arguments via support.
            System.out.println("The argument link has been added to the database.");
            argumentLinkRepository.insertNewArgumentLink(connection, arg1, arg2, "support");
        } else if (choice == 2) {
            // Check if the two arguments are part of the same discourse.
            // If they are, they cannot be linked via contradiction. Else, create an argument link via contradiction.
            String arg1discourse = argumentRepository.getDiscourseNameFromArgument(connection, arg1);
            String arg2discourse = argumentRepository.getDiscourseNameFromArgument(connection, arg2);
            if (arg1discourse.equals(arg2discourse)) {
                System.out.println("You cannot contradict two arguments from the same discourse.");
                return;
            } else {
                System.out.println("The argument link has been added to the database.");
                argumentLinkRepository.insertNewArgumentLink(connection, arg1, arg2, "contradiction");
            }
        } else {
            System.out.println("That is not a valid choice.");
            return;
        }
    }


}
