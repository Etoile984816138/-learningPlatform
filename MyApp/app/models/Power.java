package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created by C_C on 2017/6/27.
 */
@Entity
public class Power extends Model {

    public int authority_id;
    @OneToOne
    public Employee employee;

    public Power(int authority_id, Employee employee) {
        this.authority_id = authority_id;
        this.employee = employee;
    }
}
