package service.recommend;

import com.alibaba.fastjson.JSON;
import models.Course;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by jsh3 on 17-6-28.
 */
public abstract class AbstarctEasyRec {

    private final String API_KEY = "11cf2b4b08180ee56a7e25a0dc655740";
    private final String TENANT_ID = "test";

    public String url;
    public long userid;
    public int offset;
    public int numberOfResults;
    public String sessionid;

    public List<Course> doGet(CloseableHttpClient client) {
        HttpGet get = new HttpGet(makeURL());
        List<Course> recommendations = Collections.emptyList();
        try {
            CloseableHttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String line = EntityUtils.toString(entity);
                if (line.substring(0,1).equals("{")){
                    EasyRecResponse easyRecResponse = JSON.parseObject(line, EasyRecResponse.class);
                    recommendations = easyRecResponse.getRecommendedItems();
                }
                EntityUtils.consume(entity);
            }
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recommendations;
    }

    protected String makeURL() {
        assert url != null;
        StringBuilder url = new StringBuilder(this.url + "?");
        url.append("apikey=").append(API_KEY)
                .append("&tenantid=").append(TENANT_ID)
                .append("&sessionid=").append(sessionid)
                .append("&userid=").append(userid);
        if (offset != 0) url.append("&offset=").append(offset);
        if (numberOfResults != 0) url.append("&numberOfResults=").append(numberOfResults);
        return url.toString();
    }




    public AbstarctEasyRec(String url, long userid, int numberOfResults, String sessionid) {
        this.url = url;
        this.userid = userid;
        this.numberOfResults = numberOfResults;
        this.sessionid = sessionid;
    }

    public AbstarctEasyRec() {
    }
}
