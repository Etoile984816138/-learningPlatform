package models;

import play.db.jpa.Model;

import javax.persistence.*;
/**
 * Created by C_C on 2017/6/29.
 */
@Entity
public class Comment extends Model {

    public int grate;//点赞
    public String content;
    public String time;
    public boolean isRead;

    @OneToOne
    public Employee to;

    @OneToOne
    public Employee from;

    @ManyToOne
    public Discuss discuss;

    public Comment(int grate, String content, String time, boolean isRead,
                   Employee to, Employee from, Discuss discuss) {
        this.grate = grate;
        this.content = content;
        this.time = time;
        this.isRead = isRead;
        this.to = to;
        this.from = from;
        this.discuss = discuss;
    }
}
