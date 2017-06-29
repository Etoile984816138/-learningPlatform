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

    @OneToOne(mappedBy = "employee")
    public Power power;

    @OneToMany(mappedBy = "employee")
    public List<History> histories;

//    @OneToMany(mappedBy = "employee")
//    List<Comment> comments;



    public Employee() {
    }
}
