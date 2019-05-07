package unit;

import debates.repositories.DiscourseRepository;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DiscourseRepositoryTest {

    private DiscourseRepository testRepository = new DiscourseRepository();

    private Connection connection;

    @Before
    public void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:assignment2");
    }

    @Test
    public void nameExistsTest1() throws SQLException {
        String existingName = "discourse_one";
        Boolean expectedResult = true;

        boolean actualResult = testRepository.nameExists(connection, existingName);
        assert (expectedResult == actualResult);
    }

    @Test
    public void nameExistsTest2() throws SQLException {
        String existingName = "discourse_onemillion";
        Boolean expectedResult = false;

        boolean actualResult = testRepository.nameExists(connection, existingName);
        assert (expectedResult == actualResult);
    }



}
