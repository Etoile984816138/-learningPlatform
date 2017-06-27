package models;

import play.db.jpa.Model;

import javax.persistence.*;

/**
 * Created by C_C on 2017/6/26.
 */
@Entity
public class Direction extends Model{
    public String name;

    public Direction(String name) {
        this.name = name;
    }
}
