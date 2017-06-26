import org.junit.*;
import play.test.*;
import models.*;

import java.util.List;

/**
 * Created by C_C on 2017/6/26.
 */
public class TestDirection extends UnitTest {
    @Test
    public void findAll(){
        List<Direction> directions = Direction.findAll();
        for(Direction direction:directions){
            System.out.print(direction.name);
        }
    }
}
