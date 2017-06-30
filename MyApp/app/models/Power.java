package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
<<<<<<< HEAD
import javax.persistence.OneToMany;
=======
import javax.persistence.ManyToOne;
>>>>>>> b3382aa2780343ebef80e390039dd614e85d1658
import javax.persistence.OneToOne;

/**
 * Created by C_C on 2017/6/27.
 */
@Entity
public class Power extends Model {
<<<<<<< HEAD
    @OneToOne
    public Employee employee;

    @OneToMany
=======

    @OneToOne
    public Employee employee;

    @ManyToOne
>>>>>>> b3382aa2780343ebef80e390039dd614e85d1658
    public Authority authority;

    public Power(Employee employee, Authority authority) {
        this.employee = employee;
        this.authority = authority;
    }
}
