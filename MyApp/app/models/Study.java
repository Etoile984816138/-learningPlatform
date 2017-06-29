package models;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by jsh3 on 17-6-29.
 * 对应参加课程表
 */
@Entity
@DynamicUpdate
@DynamicInsert
public class Study extends Model {

    @OneToOne
    @NotNull
    public Employee employee;
    @OneToOne
    @NotNull
    public Course course;

    public Date stime;

    public Study(Employee employee, Course course, Date stime) {
        this.employee = employee;
        this.course = course;
        this.stime = stime;
    }


}
