package service.recommend;

/**
 * Created by jsh3 on 17-6-29.
 */
public class JDBCRecommendFactory implements RecommendFactory {

    @Override
    public RecommendService create() {
        return new JDBCRecommend();
    }
}
