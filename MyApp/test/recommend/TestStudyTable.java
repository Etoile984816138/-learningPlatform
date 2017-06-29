package recommend;

import models.Study;
import org.junit.Test;
import play.test.UnitTest;

/**
 * Created by jsh3 on 17-6-29.
 */
public class TestStudyTable extends UnitTest {

    @Test
    public void testCreate() {
        new Study("1")
    }

}
