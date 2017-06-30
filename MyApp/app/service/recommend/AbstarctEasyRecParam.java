package service.recommend;

/**
 * Created by jsh3 on 17-6-28.
 */
public abstract class AbstarctEasyRecParam implements EasyRecParam {

    private final String API_KEY = "11cf2b4b08180ee56a7e25a0dc655740";
    private final String TENANT_ID = "test";

    public String url;
    public long userid;
    public int offset;
    public int numberOfResults;
    public String sessionid;

    protected String removeAllNull(String url) {
        return url.replaceAll("null", "");
    }
}
