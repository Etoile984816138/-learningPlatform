package models;

import play.db.jpa.Model;

import javax.persistence.*;
/**
 * Created by C_C on 2017/6/29.
 */
public class Friend  extends Model{
    public int employee_id1;
    public int employee_id2;

    public Friend(int employee_id1, int employee_id2) {
        this.employee_id1 = employee_id1;
        this.employee_id2 = employee_id2;
    }
}
