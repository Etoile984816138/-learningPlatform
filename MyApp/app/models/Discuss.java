package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by C_C on 2017/6/29.
 */
@Entity
public class Discuss extends Model {

    public int type;//类型（1：综合讨论区；2：教师答疑区）
    public int flag;//（0：所有人；1：仅好友可见；2：私密）
    public int isNote;//（是否属于划重点或者疑问:0-是；1-不是）
    public int isNick;//0 不是 1 是

    public int grate;//点赞
    public int view;//浏览

    public String title;
    public String content;
    public String point_time;
    public String time;

    @OneToOne
    public Employee employee;

    @ManyToOne
    public Information information;

    @OneToMany(mappedBy = "discuss")
    public List<Comment> comments;

    public Discuss(int type, int flag, int isNote, int isNick, int grate, int view, String title,
                   String content, String point_time, String time, Employee employee,
                   Information information, List<Comment> comments) {
        this.type = type;
        this.flag = flag;
        this.isNote = isNote;
        this.isNick = isNick;
        this.grate = grate;
        this.view = view;
        this.title = title;
        this.content = content;
        this.point_time = point_time;
        this.time = time;
        this.employee = employee;
        this.information = information;
        this.comments = comments;
    }
}
