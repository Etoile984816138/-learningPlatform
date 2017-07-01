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
    public boolean isRead; //0 没读过 1 读过


    @OneToOne
    public Employee to;

    @OneToOne
    public Employee from;

    @ManyToOne
    public Discuss discuss;


}
