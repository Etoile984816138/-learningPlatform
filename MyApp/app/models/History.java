package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Created by jsh3 on 17-6-29.
 */
@Entity
public class History extends Model {

    public String time;
    public String point_time;

    @ManyToOne
    public Information information;

    @ManyToOne
    public Employee employee;


}
