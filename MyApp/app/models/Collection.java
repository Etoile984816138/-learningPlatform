package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by jsh3 on 17-6-30.
 */
@Entity
public class Collection extends Model {
    public String time;

    @ManyToOne
    public Employee employee;

    @ManyToOne
    public Course course;

    public Collection(String time, Employee employee, Course course) {
        this.time = time;
        this.employee = employee;
        this.course = course;
    }
}
