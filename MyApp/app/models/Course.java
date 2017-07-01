package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by C_C on 2017/6/26.
 */
@Entity
public class Course extends Model {

    public int d_id;
    public int g_id;
    public int s_id;
    public int person;//课程参加人数
    public String title;
    public String description;
    public String cover;
    public String time;

    @OneToMany(mappedBy = "course")
    public List<Chapter> chapters;

    @OneToMany(mappedBy = "course")
    public List<Power> powers;

    @OneToMany(mappedBy = "course")
    public List<Exam> exams;

    @OneToMany(mappedBy = "course")
    public List<CourseRate> courseRates;

    @OneToMany(mappedBy = "course")
    public List<Study> studies;

    @Transient
    public List<Employee> employees;

    @Transient
    public int star;

    @Transient
    public boolean hasjoin;


}
