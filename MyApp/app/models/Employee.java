package models;

import org.hibernate.annotations.DynamicInsert;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * Created by C_C on 2017/6/27.
 */
@Entity
@DynamicInsert
public class Employee extends Model{

    public String IDCard;
    public String password;
    public String phone;
    public String email;
    public String port;
    public String name;
    public String sex;
    public String department;
    public int total_time;
    public int auth;//0管理员1 学生 ；2 老师


    @OneToOne(mappedBy = "employee")
    public Tag tag;

    @OneToMany(mappedBy = "employee")
    public List<Power> powers;

    @OneToMany(mappedBy = "employee")
    public List<History> histories;

    @OneToMany(mappedBy = "employee")
    public List<Exam> exams;

    @OneToMany(mappedBy = "employee")
    public List<CourseRate> courseRates;

    @OneToMany(mappedBy = "employee")
    public List<Study> studies;

    @OneToMany(mappedBy = "employee")
    public List<Feedback> feedbacks;
}
