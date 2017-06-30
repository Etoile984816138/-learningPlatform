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
    public boolean isTeacher;

    @OneToOne(mappedBy = "employee")
    public Power power;

    @OneToMany(mappedBy = "employee")
    public List<History> histories;

<<<<<<< HEAD
    public Employee(String IDCard, String password, String phone, String email, String port,
                    String name, String sex, String department, int total_time,
                    boolean isTeacher, Power power, List<History> histories) {
        this.IDCard = IDCard;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.port = port;
        this.name = name;
        this.sex = sex;
        this.department = department;
        this.total_time = total_time;
        this.isTeacher = isTeacher;
        this.power = power;
        this.histories = histories;
    }
=======
//    @OneToMany(mappedBy = "employee")
//    List<Comment> comments;

>>>>>>> b3382aa2780343ebef80e390039dd614e85d1658

    public Employee() {
    }

    public Employee(String IDCard, String password, String phone, String email, String port, String name, String sex, String department, int total_time, Power power, List<History> histories) {
        this.IDCard = IDCard;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.port = port;
        this.name = name;
        this.sex = sex;
        this.department = department;
        this.total_time = total_time;
        this.power = power;
        this.histories = histories;
    }
}
