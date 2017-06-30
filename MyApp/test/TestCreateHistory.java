import models.History;
import models.Information;
import org.junit.Test;
import play.test.UnitTest;

/**
 * Created by jsh3 on 17-6-29.
 */
public class TestCreateHistory extends UnitTest {




    @Test
    public void saveHistory() {
        new History("", "", null, null).save();
    }
}
