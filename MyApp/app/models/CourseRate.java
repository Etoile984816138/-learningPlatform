package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by jsh3 on 17-6-30.
 */
@Entity
public class CourseRate extends Model {

    public String content;
    public int rate;

    @ManyToOne
    public Employee employee;

    @ManyToOne
    public Course course;
}
