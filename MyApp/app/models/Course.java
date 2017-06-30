package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * Created by C_C on 2017/6/26.
 */
@Entity
public class Course extends Model {
    public int d_id;
    public int g_id;
    public int s_id;

    public int person;//课程参加人数

    public String title;
    public String description;
    public String cover;
    public String time;

    @OneToMany(mappedBy = "course")
    public List<Chapter> chapters;

    @OneToOne
    public Authority authority;


    public Course(int d_id, int g_id, int s_id, int person, String title, String description,
                  String cover, String time, List<Chapter> chapters, Authority authority) {
        this.d_id = d_id;
        this.g_id = g_id;
        this.s_id = s_id;
        this.person = person;
        this.title = title;
        this.description = description;
        this.cover = cover;
        this.time = time;
        this.chapters = chapters;
        this.authority = authority;
    }

    public Course() {
    }


}
