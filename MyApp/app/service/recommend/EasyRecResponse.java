package service.recommend;

import models.Course;

import java.util.List;

/**
 * Created by jsh3 on 17-6-29.
 */
public class EasyRecResponse {


    /**
     * action : recommendationsforuser
     * userid : 1
     * sessionid : null
     * recommendedItems : []
     * tenantid : EASYREC_DEMO
     * baseitem : null
     */

    private String action;
    private String userid;
    private Object sessionid;
    private String tenantid;
    private Object baseitem;
    private List<Course> recommendedItems;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Object getSessionid() {
        return sessionid;
    }

    public void setSessionid(Object sessionid) {
        this.sessionid = sessionid;
    }

    public String getTenantid() {
        return tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
    }

    public Object getBaseitem() {
        return baseitem;
    }

    public void setBaseitem(Object baseitem) {
        this.baseitem = baseitem;
    }

    public List<Course> getRecommendedItems() {
        return recommendedItems;
    }

    public void setRecommendedItems(List<Course> recommendedItems) {
        this.recommendedItems = recommendedItems;
    }
}
