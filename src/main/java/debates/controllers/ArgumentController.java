package debates.controllers;



import debates.models.Discourse;
import debates.repositories.ArgumentRepository;
import debates.repositories.DiscourseRepository;

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
     * The discourseRepository handling database-level operations for discourses.
     */
    private DiscourseRepository discourseRepository = new DiscourseRepository();

    /**
     * The argumentRepository handling database-level operations for arguments.
     */
    private ArgumentRepository argumentRepository = new ArgumentRepository();


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

            while (!(response.equals(YES) || response.equals(NO))) {
                System.out.println("Your response must be a 'y' or a 'n'. Try again.");
                response = userResponse.nextLine();
            }

            if (response.equals(YES)) {
                createArgument(connection);
            } else if (response.equals(NO)) {
                //TODO: return to main selection
                return;
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

        // Ask user to specify a discourse, then scan for a response.
        Scanner input = new Scanner(System.in);
        System.out.println("Please select a discourse");
        String d = input.nextLine();
        if (discourseRepository.nameExists(connection, d)) {
            Discourse discourse = discourseRepository.getDiscourse(connection, d);
            int discourseLength = discourse.getText().length();

            // Ask user to specify start indices within the discourse, then scan for a response.
            System.out.println("Please type a start index in the discourse.");
            int start = input.nextInt();
            // Ask user to specify end indices within the discourse, then scan for a response.
            System.out.println("Please type an end index in the discourse.");
            int end = input.nextInt();

            // Check that the start and end indices are within length of the discourse,
            // and the end indices are after the start indices.
            if (start < discourseLength && end < discourseLength && start < end) {

                // Check that the specified indices do not already exist with an argument.
                if (!argumentRepository.argumentExists(connection, start, end)) {

                    // Add a new argument to the database.
                    String rephrasing = discourse.getText().substring(start, end);
                    argumentRepository.insertNewArgument(connection, rephrasing, start, end);
                    System.out.println("The argument, " + rephrasing + ", has been added successfully.");

                } else if (argumentRepository.argumentExists(connection, start, end)) {
                    System.out.println("The argument already exists within the database. Please try again.");
                    createArgument(connection);
                } else {
                    System.out.println("There was an issue with your input. Please try again.");
                    createArgument(connection);
                }

            }

        } else {
            System.out.println("There was an issue with your input. Please try again.");
            createArgument(connection);
        }
    }

    public void whileLoop(Connection connection) throws SQLException {
        // Ask user to specify a discourse, then scan for a response.
        Scanner input = new Scanner(System.in);
        System.out.println("Please select a discourse.");
        String d = input.nextLine();
        while (!discourseRepository.nameExists(connection, d)) {
            System.out.println("That discourse does not exist. Please try again.");
            d = input.nextLine();
        }
        Discourse discourse = discourseRepository.getDiscourse(connection, d);
        int discourseLength = discourse.getText().length();

        System.out.println("Please type a start index in the discourse.");
        int start = input.nextInt();
        while (!(start < discourseLength)) {
            System.out.println("That number is out of range. Please try again.");
            start = input.nextInt();
        }

        // Ask user to specify end indices within the discourse, then scan for a response.
        System.out.println("Please type an end index in the discourse.");
        int end = input.nextInt();
        while (!(end < discourseLength && start < end)) {
            System.out.println("That number is out of range. Please try again.");
            end = input.nextInt();
        }

        // Check that the specified indices do not already exist with an argument.
        while (argumentRepository.argumentExists(connection, start, end)) {
            System.out.println("The argument already exists within the database. Please try again.");
            // Ask user to specify start indices within the discourse, then scan for a response.
            System.out.println("Please type a start index in the discourse.");
            start = input.nextInt();
            while (!(start < discourseLength)) {
                System.out.println("That number is out of range. Please try again.");
                start = input.nextInt();
            }
            // Ask user to specify end indices within the discourse, then scan for a response.
            System.out.println("Please type an end index in the discourse.");
            end = input.nextInt();
            while (!(end < discourseLength && start < end)) {
                System.out.println("That number is out of range. Please try again.");
                end = input.nextInt();
            }
        }

        // Add a new argument to the database.
        String rephrasing = discourse.getText().substring(start, end);
        argumentRepository.insertNewArgument(connection, rephrasing, start, end);
        System.out.println("The argument, " + rephrasing + ", has been added successfully.");
    }

}
