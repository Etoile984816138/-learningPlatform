package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by C_C on 2017/6/26.
 */
@Entity
public class Course extends Model {
    public Direction direction;
    public Generic generic;
    public Style style;
    public String title;
    public String description;
    public String cover;
    public int click;
    public int authority;
    public String time;

    public Course(Direction direction, Generic generic, Style style, String title, String description, String cover, int click, int authority, String time) {
        this.direction = direction;
        this.generic = generic;
        this.style = style;
        this.title = title;
        this.description = description;
        this.cover = cover;
        this.click = click;
        this.authority = authority;
        this.time = time;
    }
}
