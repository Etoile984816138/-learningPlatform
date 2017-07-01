package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by jsh3 on 17-6-29.
 */
@Entity
public class History extends Model {

    public String cover;
    public String time;
    public String point;//已经看的时间/页数

    @ManyToOne
    public Lesson lesson;

    @ManyToOne
    public Employee employee;

    public History(String cover, String time, String point, Lesson lesson, Employee employee) {
        this.cover = cover;
        this.time = time;
        this.point = point;
        this.lesson = lesson;
        this.employee = employee;
    }
}
