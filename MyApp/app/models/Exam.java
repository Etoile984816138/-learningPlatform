package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by jsh3 on 17-7-1.
 */
@Entity
public class Exam extends Model {

    public String time;
    public int grade;

    @ManyToOne
    public Employee employee;

    @ManyToOne
    public Course course;
}
