package service.recommend;

import models.Course;
import models.Study;
import net.sf.ehcache.search.aggregator.Count;
import play.db.jpa.JPA;

import java.util.List;
import java.util.Random;

/**
 * Created by jsh3 on 17-6-29.
 */
@Deprecated
public class RandomStrategy {


    public List<Course> randomRecommend(int limit) {
        long courseNum = Course.count();
        int rate = caculateRate(limit, courseNum);
        int id = getRandom(rate);
        return Course.all().from(id).fetch(limit);
    }

    private int getRandom(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    private int caculateRate(int limit, long total) {
        return (int) (total - limit) ;
    }
}
