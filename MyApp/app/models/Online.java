package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Created by jsh3 on 17-7-1.
 * 在线客服
 */
@Entity
public class Online extends Model {

    public String content;
    public String time;
    public boolean isRead; //0 没读过 1 读过

    @ManyToOne
    public Employee employee;
}
