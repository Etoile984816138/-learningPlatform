package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by C_C on 2017/6/27.
 */
@Entity
public class Power extends Model {
    public int group_id;
    public int employee_id;
}
