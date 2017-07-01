package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by C_C on 2017/6/27.
 */
@Entity
public class Power extends Model {

    @ManyToOne
    public Employee employee;

    @ManyToOne
    public Course course;


}
