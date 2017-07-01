package recommend;

import models.Course;
import models.Employee;
import models.Study;
import org.junit.Before;
import org.junit.Test;
import play.test.UnitTest;

/**
 * Created by jsh3 on 17-6-29.
 */
public class TestStudyTable extends UnitTest {

    Employee employee =
            new Employee("","","","","","","","",0);
    Course course = new Course(0,1,2,3,4,"","","","");

    @Before
    public void set() {
        employee.save();
        course.save();
    }

    @Test
    public void testCreate() {
        Study study = new Study(employee, course, null).save();
        assertNotNull(study);
    }

}
