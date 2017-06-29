package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by C_C on 2017/6/27.
 */
@Entity
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

    public Employee(String IDCard, String password, String phone, String email, String port, String name, String sex, String department, int total_time) {
        this.IDCard = IDCard;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.port = port;
        this.name = name;
        this.sex = sex;
        this.department = department;
        this.total_time = total_time;
    }
}
