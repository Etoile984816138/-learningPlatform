package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Created by C_C on 2017/6/27.
 */
@Entity
public class Power extends Model {
    @OneToOne
    public Employee employee;

    @OneToMany
    public Authority authority;

    public Power(Employee employee, Authority authority) {
        this.employee = employee;
        this.authority = authority;
    }
}
