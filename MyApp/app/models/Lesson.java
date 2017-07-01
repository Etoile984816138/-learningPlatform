package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by C_C on 2017/6/29.
 * 课时
 */
@Entity
public class Lesson extends Model {

    public int chapter;//第几章节
    public int number;//第几课时

    public String title;//标题

    @OneToOne
    public Material material;

    @ManyToOne
    public Course course;

    @OneToMany(mappedBy = "lesson")
    public List<History> histories;
}
