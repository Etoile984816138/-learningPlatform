import models.History;
import org.junit.Test;
import play.test.UnitTest;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by jsh3 on 17-6-30.
 */
public class TestHistory extends UnitTest {

    @Test
    public void testFind() {
        List<History> histories = History.all().fetch();
        EntityManager manager = History.em();
        List<History> histories1 =
                manager.createQuery("from History h", History.class).getResultList();
    }


}
