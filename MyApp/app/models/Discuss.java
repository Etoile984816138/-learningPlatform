package models;

import play.db.jpa.Model;

import javax.persistence.*;

/**
 * Created by C_C on 2017/6/29.
 */
@Entity
public class Discuss extends Model{
    public int employee_id;
    public int material_id;

    public int type;//类型（0：综合讨论区；1：教师答疑区）
    public int flag;//（0：所有人；1：仅好友可见；2：私密）；

    public int number;//评论人数

    public String content;
    public String point_time;
    public String time;

    public Discuss(int employee_id, int material_id, int type, int flag, int number, String content, String point_time, String time) {
        this.employee_id = employee_id;
        this.material_id = material_id;
        this.type = type;
        this.flag = flag;
        this.number = number;
        this.content = content;
        this.point_time = point_time;
        this.time = time;
    }
}
