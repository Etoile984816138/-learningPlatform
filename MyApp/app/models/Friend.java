package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by C_C on 2017/6/29.
 */
@Entity
public class Friend  extends Model{

    @OneToOne
    public Employee from;
    @OneToOne
    public Employee to;

    public Friend(Employee from, Employee to) {
        this.from = from;
        this.to = to;
    }

}
