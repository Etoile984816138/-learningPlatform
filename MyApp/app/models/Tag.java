package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * Created by jsh3 on 17-7-1.
 */
@Entity
public class Tag extends Model {

    public String name;

    @OneToOne
    public Employee employee;
}
