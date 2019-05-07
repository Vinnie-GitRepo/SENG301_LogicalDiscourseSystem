package debates.repositories;

import debates.models.Actor;
import debates.models.Organisation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class handling actors at a database level.
 */
public class ActorRepository {

    /**
     * Used for queries related to organisations, as organisations are a part of affiliations.
     */
    private OrganisationRepository organisationRepository = new OrganisationRepository();


    /**
     * Checks if an actor within the database has the same names as a new actor being created.
     * Used when naming a new actor being registered, determining whether a confirmation message needs displaying.
     * @param connection A non-null connection to the database.
     * @param firstName The new first name being compared with existing actors through a query.
     * @param lastName The new last name being compared with existing actors through a query.
     * @return true if an existing actor has the same names, or false otherwise.
     * @throws SQLException The exception thrown if any issues occur when working with the database.
     */
    public boolean isHomonym(Connection connection, String firstName, String lastName) throws SQLException {

        // Query the database for an actor with names equivalent to those of the new actor being registered.
        PreparedStatement check = connection.prepareStatement("SELECT * FROM actor WHERE first_name = ? AND last_name = ?");
        check.setString(1, firstName);
        check.setString(2, lastName);
        ResultSet set = check.executeQuery();
        check.closeOnCompletion();

        // If the query returns anything, then return true, else false.
        return (set.next() == true);
    }


    /**
     * Inserts a new actor into the database.
     * @param connection A non-null connection to the database.
     * @param actor The actor being inserted into the database.
     * @throws SQLException The exception thrown if any issues occur when working with the database.
     */
    public void insertNewActor(Connection connection, Actor actor) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO actor VALUES (?,?)");
        statement.setString(1, actor.getFirstname());
        statement.setString(2, actor.getLastname());
        statement.executeUpdate();
        statement.closeOnCompletion();
    }


    /**
     * Retrieves a list of homonym actors so that their details can be presented to a user creating a homonym actor
     * @param connection A non-null connection to the database.
     * @param actor The actor whose name corresponds to other actors already stored on the database.
     * @return A list of Actors whose names match an actor being created, which includes their affiliations.
     * @throws SQLException The exception thrown if any errors occur when working with the database.
     */
    public List<Actor> retrieveHomonymActors(Connection connection, Actor actor) throws SQLException{

        // Find the actors whose names match those of 'actor'.
        PreparedStatement actorStatement = connection.prepareStatement("SELECT * " +
                                                                            "FROM actor " +
                                                                            "WHERE first_name = ? " +
                                                                            "AND last_name = ?");
        actorStatement.setString(1, actor.getFirstname());
        actorStatement.setString(2, actor.getLastname());
        ResultSet actorSet = actorStatement.executeQuery();
        actorStatement.closeOnCompletion();

        // The list of homonym actors who will have their details presented to a user when they create a homonym actor.
        List<Actor> homonymActors = new ArrayList<>();

        while (actorSet.next()) {

            // Get the name of each homonym actor in the result set.
            String firstName = actorSet.getNString("fname");
            String lastName = actorSet.getNString("lname");

            // An actor with 
            Actor homonymActor = new Actor(firstName, lastName);

            // Get the affiliations for each homonym actor.
            String actorId = actorSet.getNString("id");
            PreparedStatement affiliationStatement = connection.prepareStatement("SELECT * " +
                                                                                      "FROM Affiliation " +
                                                                                      "WHERE id = ?");
            affiliationStatement.setString(1, actorId);
            ResultSet affiliationSet = affiliationStatement.executeQuery();
            while (affiliationSet.next()) {
                String role = affiliationSet.getNString("role");
                Date startDate = affiliationSet.getDate("start");
                Date endDate = affiliationSet.getDate("end");
                String organisationText = affiliationSet.getNString("organisation");

                // Get the organisation for each affiliation
                Organisation organisation = organisationRepository.retrieveOrganisation(connection, organisationText);

                // Insert the affiliation into the homonym actor's list of affiliations.
                homonymActor.insertAffiliation(role, startDate, endDate, organisation);
            }
            // Add a homonym actor to the result.
            homonymActors.add(homonymActor);
        }
        return homonymActors;
    }

}
