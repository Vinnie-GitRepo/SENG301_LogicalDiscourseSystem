package debates.controllers;

import debates.repositories.DiscourseRepository;

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
     * The repository handling database-level operations for actors.
     */
    private DiscourseRepository repository = new DiscourseRepository();





}
