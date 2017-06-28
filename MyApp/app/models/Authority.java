package models;

import play.db.jpa.*;

import javax.persistence.*;

/**
 * Created by C_C on 2017/6/27.
 */
@Entity
public class Authority extends Model {

    public String team;

    public Authority(String team) {
        this.team = team;
    }
}
