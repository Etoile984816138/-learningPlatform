package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Created by jsh3 on 17-6-29.
 */
@Entity
public class History extends Model {

    public String cover;
    public String time;
    public String point;//已经看的时间/页数

    @ManyToOne
    public Information information;

    @ManyToOne
    public Employee employee;

    public History(String cover, String time, String point, Information information, Employee employee) {
        this.cover = cover;
        this.time = time;
        this.point = point;
        this.information = information;
        this.employee = employee;
    }
}
