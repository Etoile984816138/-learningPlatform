package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by jsh3 on 17-6-30.
 */
@Entity
public class New extends Model {

    public String content;
    public String time;
    int type; // 1 代表好友通知 2代表系统通知
    boolean isRead; //0 没读

    @ManyToOne
    public Employee from;

    @ManyToOne
    public Employee to;


}
