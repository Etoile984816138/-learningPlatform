package models;

import play.db.jpa.Model;

import javax.persistence.*;
/**
 * Created by C_C on 2017/6/29.
 */
public class Comment extends Model {
    public int discuss_id;
    public int employee_id;

    public String content;
    public String time;

    public Comment(int discuss_id, int employee_id, String content, String time) {
        this.discuss_id = discuss_id;
        this.employee_id = employee_id;
        this.content = content;
        this.time = time;
    }
}
