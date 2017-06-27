package models;

import play.db.jpa.*;

import javax.persistence.*;

/**
 * Created by C_C on 2017/6/27.
 */
@Entity
public class Group extends Model {
    public String team;

    public Group(String team) {
        this.team = team;
    }
}
