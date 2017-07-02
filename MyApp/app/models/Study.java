package models;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by jsh3 on 17-6-29.
 * 对应参加课程表
 */
@Entity
public class Study extends Model {

    public String time;

    @ManyToOne
    public Employee employee;

    @ManyToOne
    public Course course;

    @Transient
    public int hasStudy;//已经学习到第几章

    @Transient
    public int total;

    public Study(String time, Employee employee, Course course) {
        this.time = time;
        this.employee = employee;
        this.course = course;
    }
}
