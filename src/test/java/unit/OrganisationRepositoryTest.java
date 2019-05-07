package unit;

import debates.repositories.OrganisationRepository;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OrganisationRepositoryTest {

    private OrganisationRepository testRepository = new OrganisationRepository();

    private Connection connection;

    @Before
    public void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:assignment2");
    }

    @Test
    public void nameExistsTest1() throws SQLException {
        String existingName = "Orgg";
        Boolean expectedResult = true;

        boolean actualResult = testRepository.nameExists(connection, existingName);
        assert (expectedResult == actualResult);
    }

    @Test
    public void nameExistsTest2() throws SQLException {
        String existingName = "Not an organisation which exists within our database Ltd.";
        Boolean expectedResult = false;

        boolean actualResult = testRepository.nameExists(connection, existingName);
        assert (expectedResult == actualResult);
    }





}
