package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by C_C on 2017/6/27.
 */
@Entity
public class Power extends Model {

    public int authority_id;
    public int employee_id;

    public Power(int authority_id, int employee_id) {
        this.authority_id = authority_id;
        this.employee_id = employee_id;
    }
}
