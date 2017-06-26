package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by C_C on 2017/6/26.
 */
@Entity
public class Generic extends Model {
    public Direction direction;
    public String name;

    public Generic(Direction direction, String name) {
        this.direction = direction;
        this.name = name;
    }
}
