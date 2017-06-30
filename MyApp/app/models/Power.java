package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Created by C_C on 2017/6/27.
 */
@Entity
public class Power extends Model {

    @OneToOne
    public Employee employee;

    @ManyToOne
    public Authority authority;

    public Power(Employee employee, Authority authority) {
        this.employee = employee;
        this.authority = authority;
    }
}
