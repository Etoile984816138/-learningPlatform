package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by C_C on 2017/6/26.
 */
@Entity
public class Style extends Model {
    public Generic generic;
    public String name;

    public Style(Generic generic, String name) {
        this.generic = generic;
        this.name = name;
    }
}
