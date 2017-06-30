import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

/**
 * Created by jsh3 on 17-6-30.
 */
public class TestDataTable extends UnitTest {

    @Before
    public void setUp() {
        Fixtures.deleteDatabase();
        Fixtures.loadModels("data.yml");
    }


    @Test
    public void a() {
        assert 1 == 1;
    }
}
