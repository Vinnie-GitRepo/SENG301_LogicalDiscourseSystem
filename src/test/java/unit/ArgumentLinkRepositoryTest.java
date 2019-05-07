package unit;

import debates.repositories.ArgumentLinkRepository;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ArgumentLinkRepositoryTest {

    private ArgumentLinkRepository testRepository = new ArgumentLinkRepository();

    private Connection connection;

    @Before
    public void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:assignment2");
    }

    @Test
    public void argumentExistsTest1() throws SQLException {
        Boolean expectedResult = true;

        boolean actualResult = testRepository.argumentLinkExists(connection, 1, 2);
        assert (expectedResult == actualResult);
    }

    @Test
    public void argumentExistsTest2() throws SQLException {
        Boolean expectedResult = false;

        boolean actualResult = testRepository.argumentLinkExists(connection, 1, 1);
        assert (expectedResult == actualResult);
    }

}
