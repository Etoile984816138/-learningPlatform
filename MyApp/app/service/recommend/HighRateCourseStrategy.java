package service.recommend;

/**
 * Created by jsh3 on 17-6-29.
 */
@Deprecated
public class HighRateCourseStrategy extends AbstarctEasyRec {

    public HighRateCourseStrategy(long userid, int numberOfResults, String sessionid) {
        super("http://118.89.145.76/easyrec/api/1.1/json/itemsratedgoodbyotherusers", userid, numberOfResults, sessionid);
    }
}
