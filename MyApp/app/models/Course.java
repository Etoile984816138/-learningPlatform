package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by C_C on 2017/6/26.
 */
@Entity
public class Course extends Model {
    public int d_id;
    public int g_id;
    public int s_id;
    public String title;
    public String description;
    public String cover;
    public int click;
    public int power;
    public String time;

    public Course(int d_id, int g_id, int s_id, String title, String description, String cover, int click, int power, String time) {

        this.d_id = d_id;
        this.g_id = g_id;
        this.s_id = s_id;
        this.title = title;
        this.description = description;
        this.cover = cover;
        this.click = click;
        this.power = power;
        this.time = time;
    }
}
