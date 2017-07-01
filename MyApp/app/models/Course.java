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

    public int authority;//0:所有人；1:选择可见；2:选择不可见；

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

    @OneToMany(mappedBy = "course")
    public List<Collection> collections;

    @Transient
    public List<Employee> employees;

    @Transient
    public int star;

    @Transient
    public boolean hasjoin;

    public Course(int d_id, int g_id, int s_id, int authority, String title,String description,
                  String cover, String time, List<Chapter> chapters,List<Power> powers,
                  List<Exam> exams, List<CourseRate> courseRates, List<Study> studies,List<Collection> collections) {

        this.d_id = d_id;
        this.g_id = g_id;
        this.s_id = s_id;
        this.authority = authority;
        this.title = title;
        this.description = description;
        this.cover = cover;
        this.time = time;
        this.chapters = chapters;
        this.powers = powers;
        this.exams = exams;
        this.courseRates = courseRates;
        this.studies = studies;
        this.collections = collections;
    }
}
