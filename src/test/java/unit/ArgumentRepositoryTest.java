package unit;

import debates.repositories.ArgumentRepository;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ArgumentRepositoryTest {

    private ArgumentRepository testRepository = new ArgumentRepository();

    private Connection connection;

    @Before
    public void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:assignment2");
    }

    @Test
    public void argumentExistsTest1() throws SQLException {
        Boolean expectedResult = true;

        boolean actualResult = testRepository.argumentExists(connection, 0, 4);
        assert (expectedResult == actualResult);
    }

    @Test
    public void argumentExistsTest2() throws SQLException {
        Boolean expectedResult = false;

        boolean actualResult = testRepository.argumentExists(connection, -1, -99);
        assert (expectedResult == actualResult);
    }

    @Test
    public void argumentDiscourseNotNullTest() throws SQLException {
        String expectedResult = "discourse_one";

        String actualResult = testRepository.getDiscourseNameFromArgument(connection, 1);
        assert (expectedResult.equals(actualResult));
    }

    @Test
    public void argumentDiscourseNullTest() throws SQLException {
        String expectedResult = null;

        String actualResult = testRepository.getDiscourseNameFromArgument(connection, 123456);
        assert (expectedResult.equals(actualResult));
    }

}
