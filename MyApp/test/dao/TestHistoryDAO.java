package dao;

import models.Course;
import models.Employee;
import models.History;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

import java.util.List;

/**
 * Created by jsh3 on 17-6-30.
 */
public class TestHistoryDAO extends UnitTest {

    static Employee employee = new Employee();


    @BeforeClass
    public static void setUp() {
        employee.id = 1L;
    }

    @Before
    public void initDatabase() {
        Fixtures.deleteDatabase();
        Fixtures.loadModels("data.yml");
    }

    @Test
    public void testFinallyVisit() {
        History history = HistoryDAO.finallyVisit(employee);
        assertTrue(history.information.id == 1L);
    }

    @Test
    public void testAllVisit() {
        List<History> historyList = HistoryDAO.allVisit(employee);
        assertTrue(historyList.size() == 2);
    }
}
